import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';

import { Category } from '../models/category';
import { Product } from '../models/product';
import { CategoriesService } from '../services/categories/categories.service';
import { ProductsService } from '../services/products/products.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss'],
})
export class ProductsComponent implements OnInit {
  // Category object to represent the no filter selection "Todos".
  readonly categoryNoFilter: Category = {
    _id: -1,
    name: 'All',
  };

  selectedCategory: Category = this.categoryNoFilter;

  readonly displayedColumns: string[] = [
    'description',
    'purchaseDate',
    'price',
    'actions',
  ];

  products$: Observable<Product[]>;

  categories$: Observable<Category[]>;

  constructor(
    private readonly productsService: ProductsService,
    private readonly categoriesService: CategoriesService,
    private readonly router: Router,
    private readonly route: ActivatedRoute
  ) {
    this.products$ = productsService.list();
    this.categories$ = categoriesService.list();
  }

  ngOnInit(): void {}

  private refresh() {
    this.onFilterChanged();
  }

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  onEdit(product: Product) {
    this.router.navigate(['edit', product._id], { relativeTo: this.route });
  }

  onRemove(product: Product) {
    this.productsService
      .remove(product._id)
      .subscribe((unused) => this.refresh());
  }

  onFilterChanged() {
    if (this.selectedCategory == this.categoryNoFilter) {
      this.products$ = this.productsService.list();
    } else {
      this.products$ = this.productsService.listByCategory(
        this.selectedCategory
      );
    }
  }
}
