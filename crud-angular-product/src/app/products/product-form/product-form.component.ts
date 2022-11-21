import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { Category } from '../models/category';
import { Product } from '../models/product';
import { CategoriesService } from '../services/categories/categories.service';
import { ProductsService } from '../services/products/products.service';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss'],
})
export class ProductFormComponent implements OnInit {
  readonly DESCRIPTION_MAX_LENGHT = 255;
  readonly DESCRIPTION_MIN_LENGHT = 5;

  readonly DATE_COMPLETE_LENGHT = 10;

  categories$: Observable<Category[]>;

  form = this.formBuilder.group({
    _id: [0],
    _idCategory: [0, Validators.required],
    description: [
      '',
      [
        Validators.required,
        Validators.minLength(this.DESCRIPTION_MIN_LENGHT),
        Validators.maxLength(this.DESCRIPTION_MAX_LENGHT),
      ],
    ],
    purchaseDate: [
      '',
      [Validators.required, Validators.minLength(this.DATE_COMPLETE_LENGHT)],
    ],
    price: [0, [Validators.required]],
  });

  constructor(
    private formBuilder: NonNullableFormBuilder,
    private readonly categoriesService: CategoriesService,
    private readonly route: ActivatedRoute,
    private readonly productService: ProductsService,
    private readonly snackBar: MatSnackBar,
    private readonly location: Location
  ) {
    this.categories$ = categoriesService.list();
    const product: Product = this.route.snapshot.data['product'];

    this.form.setValue({
      _id: product._id,
      _idCategory: product._idCategory,
      description: product.description,
      purchaseDate: product.purchaseDate,
      price: product.price,
    });

    this.form.markAllAsTouched();
  }

  onSubmit() {
    this.productService.save(this.form.value).subscribe(
      (result) => this.onSuccess(),
      (error) => this.onError()
    );
  }

  private onSuccess() {
    this.snackBar.open('Produto salvo com sucesso! ', '', { duration: 5000 });
    this.onCancel();
  }

  private onError() {
    this.snackBar.open('Erro ao salvar produto', '', { duration: 5000 });
  }

  onCancel() {
    this.location.back();
  }
  ngOnInit(): void {}

  descriptionErrorMessage() {
    const field = this.form.get('description');

    if (field?.hasError('required')) {
      return 'Campo obrigatório';
    }

    if (field?.hasError('minlength')) {
      return `Tamanho mínimo para descrição é de ${this.DESCRIPTION_MIN_LENGHT} caracteres.`;
    }

    if (field?.hasError('maxlength')) {
      return `Tamanho máximo para descrição é de ${this.DESCRIPTION_MAX_LENGHT} caracteres.`;
    }

    return 'Campo Inválido';
  }

  purchaseDateErrorMessage() {
    const field = this.form.get('purchaseDate');

    if (field?.hasError('required')) {
      return 'Campo obrigatório';
    }
    return 'Data incompleta!';
  }

  priceErrorMessage() {
    const field = this.form.get('price');

    if (field?.hasError('required')) {
      return 'Campo obrigatório';
    }
    return 'Valor inserido fora do formato aceito! (0.00)';
  }
}
