import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Product } from '../models/product.model';
import { ProductService } from './Product.service';

@Component({
  selector: 'app-product-read',
  templateUrl: './product.component.html',
  styles: []
})
export class ProductComponent implements OnInit {

  products: Product[];
bo:string;
  constructor(private route: ActivatedRoute,private router: Router, private productService: ProductService) {

  }
  

  ngOnInit() {
    this.bo=this.route.snapshot.params['bo']
    console.log(this.bo);
    this.productService.getProducts(this.bo)
      .subscribe( data => {
        this.products = data;
      });
  };

  deleteProduct(product: Product): void {
    this.productService.deleteProduct(product,this.bo)
      .subscribe( data => {
        this.products = this.products.filter(u => u !== product);
      })
      window.location.reload();
  };

}