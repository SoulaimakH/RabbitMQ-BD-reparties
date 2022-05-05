import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Product } from '../models/product.model';
import { ProductService } from './Product.service';

@Component({
  selector: 'app-product-create',
  templateUrl: './add-product.component.html'
})
export class AddProductComponent {

  product: Product = new Product();
bo:string;
  constructor(private router: Router, private route: ActivatedRoute, private productService: ProductService) {

  }
  ngOnInit() {
    
     this.bo=this.route.snapshot.params['bo']
     console.log(this.bo);
  };

  createProduct(): void {
    console.log(  this.product);
    this.productService.createProduct(this.product,this.bo)

        .subscribe( data => {
          alert("Product created successfully.");
          
        });
        this.router.navigate(['/products/'+this.bo]);

  };

}