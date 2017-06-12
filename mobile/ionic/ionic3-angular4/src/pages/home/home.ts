import { IonicPage } from 'ionic-angular';
import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { RestProvider } from '../../providers/rest/rest';


@IonicPage()
@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  private countries: string[];
  private errorMessage: string;

  constructor(private navCtrl: NavController, private rest: RestProvider) {

  }

  ionViewDidLoad() {
    this.getCountries();
  }

  getCountries () {
    this.rest.getCountries()
      .subscribe(
          countries => this.countries    = countries,
          error     => this.errorMessage = <any>error
      );
  }
}
