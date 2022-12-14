import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { environment } from '../../../../environments/environment';
import { Category } from '../../models/category';

@Injectable({
  providedIn: 'root',
})
export class CategoriesService {
  constructor(private readonly httpClient: HttpClient) {}

  list(): Observable<Category[]> {
    return this.httpClient.get<Category[]>(environment.API_Categories);
  }
}
