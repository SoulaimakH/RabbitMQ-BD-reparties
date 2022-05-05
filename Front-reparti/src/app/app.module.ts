import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { ProductComponent } from './Product/Product.component';
import { AppRoutingModule } from './app.routing.module';
import {ProductService} from './Product/Product.service';
import {HttpClientModule} from "@angular/common/http";
import {AddProductComponent} from './Product/add-Product.component';
import { UpdateProductComponent } from './Product/update-product.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    AddProductComponent,
    UpdateProductComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [ProductService],
  bootstrap: [AppComponent]
})
export class AppModule { }
