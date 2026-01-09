import { Injectable } from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ErrorMessageService {
  public getErrorMessage(e: unknown): string {
    if (e instanceof HttpErrorResponse) {
      if (typeof e.error === 'string') {
        return e.error;
      }
      if (e.error?.message) {
        return e.error.message;
      }
      return `Server error (${e.status})`;
    }

    if (e instanceof Error) {
      return e.message;
    }

    return 'Unexpected error';
  }
}
