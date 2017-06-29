package com.social.eshop.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.social.eshop.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.PersistentToken.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.User.class.getName() + ".persistentTokens", jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.SocialUserConnection.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Customer.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Customer.class.getName() + ".seens", jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Customer.class.getName() + ".wishLists", jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Customer.class.getName() + ".comments", jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.LoginOptions.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.StaticPages.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Manager.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Manager.class.getName() + ".manegers", jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Bucket.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Bucket.class.getName() + ".productInBuckets", jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.AddressShipping.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Seen.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.WishList.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Address.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Avatar.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.PersonalInformation.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Products.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Products.class.getName() + ".media", jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Products.class.getName() + ".comments", jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Products.class.getName() + ".productInBuckets", jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Products.class.getName() + ".tagForProducts", jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Tags.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Tags.class.getName() + ".tagForProducts", jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Consignment.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Consignment.class.getName() + ".products", jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Storage.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Storage.class.getName() + ".consignments", jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Producers.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Producers.class.getName() + ".consignments", jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Category.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Category.class.getName() + ".subCats", jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.SubCategory.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.SubCategory.class.getName() + ".products", jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Media.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Comments.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.Options.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.TagForProduct.class.getName(), jcacheConfiguration);
            cm.createCache(com.social.eshop.domain.ProductInBucket.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
