import {AbstractControl, ValidationErrors, ValidatorFn} from '@angular/forms';

export function oneOf<T extends string>(allowed: readonly T[]):ValidatorFn{
  return (control: AbstractControl): ValidationErrors | null => {
    return allowed.includes(control.value) ? null : {oneof: {allowed}};
  }
}
