import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ProductComponent } from './Product/Product.component';
import { AddProductComponent } from './Product/add-Product.component';
import { UpdateProductComponent } from './Product/update-product.component';

const routes: Routes = [
  { path: 'products/:bo', component: ProductComponent },
  { path: 'add/:bo', component: AddProductComponent },
  { path: 'update/:id/:bo', component: UpdateProductComponent },
  { path: '', redirectTo: '/products', pathMatch: 'full'}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule { }