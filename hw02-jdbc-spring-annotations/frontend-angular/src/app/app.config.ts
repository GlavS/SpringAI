import {ApplicationConfig, provideBrowserGlobalErrorListeners} from '@angular/core';
import {provideRouter} from '@angular/router';

import {routes} from './app.routes';
import {provideHttpClient} from '@angular/common/http';
import {WORKS_API} from './api/works-api-token';
import {MockApiService} from './api/service/mock-api-service';
import {HttpApiService} from './api/service/http-api-service';
import {environment} from './environment';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),
    provideHttpClient(),
    {
      provide: WORKS_API,
      useClass: environment.useMockApi? MockApiService : HttpApiService
    }
  ]
};
