import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Product } from '../models/product.model';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class ProductService {

  constructor(private http:HttpClient) {}

  private productUrl = 'http://localhost:9292/Bo/';

  public getProducts(bo:string) {
    return this.http.get<Product[]>(this.productUrl + "getallprod/"+bo);
  }

  public getProduct(id,bo:string ) {
    return this.http.get(this.productUrl + "/getprod/"+bo+"/"+id);
  }

  public deleteProduct(product,bo:string) {
    return this.http.delete(this.productUrl + "delete/"+bo+"/"+ product.id);
  }

  public createProduct(product,bo:string) {
    return this.http.post<Product>(this.productUrl + "add/"+bo, product);
  }

  public updateProduct(product,bo:string) {
    return this.http.post<Product>(this.productUrl + "update/"+bo+"/"+ product.id, product);
  }

}
