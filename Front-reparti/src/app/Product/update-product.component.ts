import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Product } from '../models/product.model';
import { ProductService } from './Product.service';

@Component({
  selector: 'app-product-update',
  templateUrl: './update-product.component.html',
})
export class UpdateProductComponent implements OnInit {

  product: any = {};
bo:string;
  constructor(private router: Router, private route: ActivatedRoute, private productService: ProductService) {
    
  }

  ngOnInit() {
    this.bo=this.route.snapshot.params['bo']
    this.productService.getProduct(this.route.snapshot.params['id'],this.bo)
      .subscribe(data => {
        this.product = data[0];
        console.log(this.product)
      });
  };

  updateProduct(): void {
    console.log(this.product)
    this.productService.updateProduct(this.product,this.bo)
      .subscribe(data => {
        alert("Product updated successfully.");
       
      });
      this.router.navigate(['/products/'+this.bo]);
  };

}