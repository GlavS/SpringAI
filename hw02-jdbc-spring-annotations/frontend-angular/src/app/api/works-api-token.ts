import {InjectionToken} from '@angular/core';
import {WorksApi} from './works-api';

export const WORKS_API = new InjectionToken<WorksApi>('WORKS_API');
