import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { environment } from '../../../../environments/environment';
import { Category } from '../../models/category';
import { Product } from '../../models/product';

@Injectable({
  providedIn: 'root',
})
export class ProductsService {
  constructor(private readonly httpClient: HttpClient) {}

  list(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(environment.API_Products);
  }

  listByCategory(category: Category): Observable<Product[]> {
    return this.httpClient.get<Product[]>(
      `${environment.API_Products}?category=${category._id}`
    );
  }

  loadById(productId: number): Observable<Product> {
    return this.httpClient.get<Product>(
      `${environment.API_Products}/${productId}`
    );
  }

  save(product: Partial<Product>): Observable<Product> {
    if (product._id) {
      return this.update(product);
    }
    return this.create(product);
  }

  private create(product: Partial<Product>) {
    return this.httpClient.post<Product>(environment.API_Products, product);
  }

  private update(product: Partial<Product>) {
    return this.httpClient.put<Product>(
      `${environment.API_Products}/${product._id}`,
      product
    );
  }

  remove(productId: number) {
    console.log(`${environment.API_Products}/${productId}`);
    return this.httpClient.delete(`${environment.API_Products}/${productId}`);
  }
}
