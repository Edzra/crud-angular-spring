import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  Resolve,
  RouterStateSnapshot,
} from '@angular/router';
import { Observable, of } from 'rxjs';

import { Product } from '../models/product';
import { ProductsService } from '../services/products/products.service';

@Injectable({
  providedIn: 'root',
})
export class ProductResolver implements Resolve<Product> {
  constructor(private readonly productsService: ProductsService) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Product> {
    if (route.params && route.params['id']) {
      return this.productsService.loadById(route.params['id']);
    }
    return of({
      _id: 0,
      _idCategory: 1,
      description: '',
      price: 0,
      purchaseDate: '',
    });
  }
}
