import { Value } from '../value';
import { Type } from '../type';
export class Options {
    constructor(
        public id?: number,
        public name?: string,
        public level?: number,
        public value?: Value,
        public type?: Type,
    ) {
    }
}
