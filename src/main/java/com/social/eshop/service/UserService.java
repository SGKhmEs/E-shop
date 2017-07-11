package com.social.eshop.service;

import com.social.eshop.domain.*;
import com.social.eshop.repository.AuthorityRepository;
import com.social.eshop.repository.PersistentTokenRepository;
import com.social.eshop.config.Constants;
import com.social.eshop.repository.UserRepository;
import com.social.eshop.repository.search.UserSearchRepository;
import com.social.eshop.security.AuthoritiesConstants;
import com.social.eshop.security.SecurityUtils;
import com.social.eshop.service.dto.*;
import com.social.eshop.service.mapper.*;
import com.social.eshop.service.util.RandomUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SocialService socialService;
    private final UserSearchRepository userSearchRepository;
    private final PersistentTokenRepository persistentTokenRepository;
    private final AuthorityRepository authorityRepository;

    @Inject
    private CustomerAccountService customerAccountService;
    @Inject
    private PersonalInformationService personalInformationService;
    @Inject
    private CustomerService customerService;
    @Inject
    private AddressService addressService;
    @Inject
    private AvatarService avatarService;
    @Inject
    private BucketService bucketService;
    @Inject
    private WishListService wishListService;
    @Inject
    private SeenService seenService;

    private CustomerAccountMapper customerAccountMapper;
    private CustomerMapper customerMapper;
    private PersonalInformationMapper personalInformationMapper;
    private AvatarMapper avatarMapper;
    private AddressMapper addressMapper;
    private BucketMapper bucketMapper;
    private WishListMapper wishListMapper;
    private SeenMapper seenMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, SocialService socialService,
                       UserSearchRepository userSearchRepository, PersistentTokenRepository persistentTokenRepository,
                       AuthorityRepository authorityRepository, CustomerAccountMapper customerAccountMapper,
                       CustomerMapper customerMapper, PersonalInformationMapper personalInformationMapper,
                       AvatarMapper avatarMapper, AddressMapper addressMapper, BucketMapper bucketMapper,
                       WishListMapper wishListMapper, SeenMapper seenMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.socialService = socialService;
        this.userSearchRepository = userSearchRepository;
        this.persistentTokenRepository = persistentTokenRepository;
        this.authorityRepository = authorityRepository;
        this.customerAccountMapper = customerAccountMapper;
        this.customerMapper = customerMapper;
        this.personalInformationMapper = personalInformationMapper;
        this.avatarMapper = avatarMapper;
        this.addressMapper = addressMapper;
        this.bucketMapper = bucketMapper;
        this.wishListMapper = wishListMapper;
        this.seenMapper = seenMapper;
    }

    public Optional<User> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        return userRepository.findOneByActivationKey(key)
            .map(user -> {
                // activate given user for the registration key.
                user.setActivated(true);
                user.setActivationKey(null);
                userSearchRepository.save(user);
                log.debug("Activated user: {}", user);
                return user;
            });
    }

    private void createdCustomerAccount (User user){

        CustomerAccount customerAccount = new CustomerAccount(user.getId());
        Customer customer = new Customer(user.getId());

        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setId(user.getId());

        Avatar avatar = new Avatar();
        avatar.setId(user.getId());

        Address address = new Address();
        address.setId(user.getId());

        Bucket bucket = new Bucket();
        bucket.setId(user.getId());

        WishList wishList = new WishList();
        wishList.setId(user.getId());

        Seen seen = new Seen();
        seen.setId(user.getId());

        customerAccount.setUser(user);
        CustomerAccountDTO customerAccountDTO = customerAccountMapper.toDto(customerAccount);
        customerAccountService.save(customerAccountDTO);

        customer.setCustomerAccount(customerAccount);
        CustomerDTO customerDTO = customerMapper.toDto(customer);
        customerService.save(customerDTO);

        personalInformation.setCustomer(customer);
        PersonalInformationDTO personalInformationDTO = personalInformationMapper.toDto(personalInformation);
        personalInformationService.save(personalInformationDTO);

        bucket.setCustomer(customer);
        BucketDTO bucketDTO = bucketMapper.toDto(bucket);
        bucketService.save(bucketDTO);

        address.setCustomer(customer);
        AddressDTO addressDTO = addressMapper.toDto(address);
        addressService.save(addressDTO);

        avatar.setCustomer(customer);
        AvatarDTO avatarDTO = avatarMapper.toDto(avatar);
        avatarService.save(avatarDTO);

        wishList.setCustomer(customer);
        WishListDTO wishListDTO = wishListMapper.toDto(wishList);
        wishListService.save(wishListDTO);

        seen.setCustomer(customer);
        SeenDTO seenDTO = seenMapper.toDto(seen);
        seenService.save(seenDTO);

        customerAccount.setCustomer(customer);
        customerAccountDTO = customerAccountMapper.toDto(customerAccount);
        customerAccountService.save(customerAccountDTO);

        customer.setPersonalInfo(personalInformation);
        customerDTO = customerMapper.toDto(customer);
        customerService.save(customerDTO);

        customer.setAvatar(avatar);
        customerDTO = customerMapper.toDto(customer);
        customerService.save(customerDTO);

        customer.setAddress(address);
        customerDTO = customerMapper.toDto(customer);
        customerService.save(customerDTO);
    }


    public Optional<User> completePasswordReset(String newPassword, String key) {
       log.debug("Reset user password for reset key {}", key);

       return userRepository.findOneByResetKey(key)
           .filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400)))
           .map(user -> {
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setResetKey(null);
                user.setResetDate(null);
                return user;
           });
    }

    public Optional<User> requestPasswordReset(String mail) {
        return userRepository.findOneByEmail(mail)
            .filter(User::getActivated)
            .map(user -> {
                user.setResetKey(RandomUtil.generateResetKey());
                user.setResetDate(Instant.now());
                return user;
            });
    }

    public User createUser(String login, String password, String firstName, String lastName, String email,
        String imageUrl, String langKey) {

        User newUser = new User();
        Authority authority = authorityRepository.findOne(AuthoritiesConstants.USER);
        Set<Authority> authorities = new HashSet<>();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(login);
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setImageUrl(imageUrl);
        newUser.setLangKey(langKey);
        // new user is not active
        newUser.setActivated(true); //in the future change to false
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        authorities.add(authority);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        userSearchRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
            switch (newUser.getLogin()) {
                case "manager1" : createdManagerAccount(newUser);
                break;
                default: createdCustomerAccount (newUser);
                break;
            }
        return newUser;
    }

    private void createdManagerAccount(User user) {
        CustomerAccount customerAccount = new CustomerAccount(user.getId());
        Customer customer = new Customer(user.getId());

        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setId(user.getId());

        Avatar avatar = new Avatar();
        avatar.setId(user.getId());

        Address address = new Address();
        address.setId(user.getId());

        customerAccount.setUser(user);
        CustomerAccountDTO customerAccountDTO = customerAccountMapper.toDto(customerAccount);
        customerAccountService.save(customerAccountDTO);

        customer.setCustomerAccount(customerAccount);
        CustomerDTO customerDTO = customerMapper.toDto(customer);
        customerService.save(customerDTO);

        personalInformation.setCustomer(customer);
        PersonalInformationDTO personalInformationDTO = personalInformationMapper.toDto(personalInformation);
        personalInformationService.save(personalInformationDTO);

        address.setCustomer(customer);
        AddressDTO addressDTO = addressMapper.toDto(address);
        addressService.save(addressDTO);

        avatar.setCustomer(customer);
        AvatarDTO avatarDTO = avatarMapper.toDto(avatar);
        avatarService.save(avatarDTO);

        customerAccount.setCustomer(customer);
        customerAccountDTO = customerAccountMapper.toDto(customerAccount);
        customerAccountService.save(customerAccountDTO);

        customer.setPersonalInfo(personalInformation);
        customerDTO = customerMapper.toDto(customer);
        customerService.save(customerDTO);

        customer.setAvatar(avatar);
        customerDTO = customerMapper.toDto(customer);
        customerService.save(customerDTO);

        customer.setAddress(address);
        customerDTO = customerMapper.toDto(customer);
        customerService.save(customerDTO);
    }


    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setImageUrl(userDTO.getImageUrl());
        if (userDTO.getLangKey() == null) {
            user.setLangKey("en"); // default language
        } else {
            user.setLangKey(userDTO.getLangKey());
        }
        if (userDTO.getAuthorities() != null) {
            Set<Authority> authorities = new HashSet<>();
            userDTO.getAuthorities().forEach(
                authority -> authorities.add(authorityRepository.findOne(authority))
            );
            user.setAuthorities(authorities);
        }
        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        user.setPassword(encryptedPassword);
        user.setResetKey(RandomUtil.generateResetKey());
        user.setResetDate(Instant.now());
        user.setActivated(true);
        userRepository.save(user);
        userSearchRepository.save(user);
        log.debug("Created Information for User: {}", user);
        return user;
    }

    /**
     * Update basic information (first name, last name, email, language) for the current user.
     *
     * @param firstName first name of user
     * @param lastName last name of user
     * @param email email id of user
     * @param langKey language key
     * @param imageUrl image URL of user
     */
    public void updateUser(String firstName, String lastName, String email, String langKey, String imageUrl) {
        userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).ifPresent(user -> {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setLangKey(langKey);
            user.setImageUrl(imageUrl);
            userSearchRepository.save(user);
            log.debug("Changed Information for User: {}", user);
        });
    }

    /**
     * Update all information for a specific user, and return the modified user.
     *
     * @param userDTO user to update
     * @return updated user
     */
    public Optional<UserDTO> updateUser(UserDTO userDTO) {
        return Optional.of(userRepository
            .findOne(userDTO.getId()))
            .map(user -> {
                user.setLogin(userDTO.getLogin());
                user.setFirstName(userDTO.getFirstName());
                user.setLastName(userDTO.getLastName());
                user.setEmail(userDTO.getEmail());
                user.setImageUrl(userDTO.getImageUrl());
                user.setActivated(userDTO.isActivated());
                user.setLangKey(userDTO.getLangKey());
                Set<Authority> managedAuthorities = user.getAuthorities();
                managedAuthorities.clear();
                userDTO.getAuthorities().stream()
                    .map(authorityRepository::findOne)
                    .forEach(managedAuthorities::add);
                userSearchRepository.save(user);
                log.debug("Changed Information for User: {}", user);
                return user;
            })
            .map(UserDTO::new);
    }



    public void deleteUser(String login) {
        userRepository.findOneByLogin(login).ifPresent(user -> {
            socialService.deleteUserSocialConnection(user.getLogin());
            userRepository.delete(user);
            userSearchRepository.delete(user);
            log.debug("Deleted User: {}", user);
        });
    }

    public void changePassword(String password) {
        userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).ifPresent(user -> {
            String encryptedPassword = passwordEncoder.encode(password);
            user.setPassword(encryptedPassword);
            log.debug("Changed password for User: {}", user);
        });
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> getAllManagedUsers(Pageable pageable) {
        return userRepository.findAllByLoginNot(pageable, Constants.ANONYMOUS_USER).map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneWithAuthoritiesByLogin(login);
    }

    @Transactional(readOnly = true)
    public User getUserWithAuthorities(Long id) {
        return userRepository.findOneWithAuthoritiesById(id);
    }

    @Transactional(readOnly = true)
    public User getUserWithAuthorities() {
        return userRepository.findOneWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin()).orElse(null);
    }

    /**
     * Persistent Token are used for providing automatic authentication, they should be automatically deleted after
     * 30 days.
     * <p>
     * This is scheduled to get fired everyday, at midnight.
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void removeOldPersistentTokens() {
        LocalDate now = LocalDate.now();
        persistentTokenRepository.findByTokenDateBefore(now.minusMonths(1)).forEach(token -> {
            log.debug("Deleting token {}", token.getSeries());
            User user = token.getUser();
            user.getPersistentTokens().remove(token);
            persistentTokenRepository.delete(token);
        });
    }

    /**
     * Not activated users should be automatically deleted after 3 days.
     * <p>
     * This is scheduled to get fired everyday, at 01:00 (am).
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedUsers() {
        List<User> users = userRepository.findAllByActivatedIsFalseAndCreatedDateBefore(Instant.now().minus(3, ChronoUnit.DAYS));
        for (User user : users) {
            log.debug("Deleting not activated user {}", user.getLogin());
            userRepository.delete(user);
            userSearchRepository.delete(user);
        }
    }

    /**
     * @return a list of all the authorities
     */
    public List<String> getAuthorities() {
        return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
    }


//    private void createdUserAccount(User user) {
//
//
//}
//
//    private void createdSmmAccount(User user) {
//    }
//
//    private void createdModeratorAccount(User user) {
//    }
//
//    private void createdManagerAccount(User user) {
//        createdCustomerAccount(user) ;
//    }

}
