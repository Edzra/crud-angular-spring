import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductsComponent } from './products/products.component';
import { ProductFormComponent } from './product-form/product-form.component';
import { ProductResolver } from './guards/product.resolver';

const routes: Routes = [
  { path: '', component: ProductsComponent },
  {
    path: 'new',
    component: ProductFormComponent,
    resolve: { product: ProductResolver },
  },
  {
    path: 'edit/:id',
    component: ProductFormComponent,
    resolve: { product : ProductResolver },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ProductsRoutingModule {}
