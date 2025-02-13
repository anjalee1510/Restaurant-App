import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { DropdownModule } from 'primeng/dropdown';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MessageModule } from 'primeng/message'
import { MessageService } from 'primeng/api';


interface RestaurantBrand{
  id: string;
  name: string;
}

interface City{
  id: string;
  name: string;
}

interface Location{
  id: string;
  name: string;
}

interface UnavailableItem {
  pId: string;
  itemName: string;
  isAvailable:boolean;
  reasonUnavailable: string;
}

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet,DropdownModule, ButtonModule, CardModule, InputTextModule,FormsModule, MessageModule],
  providers: [MessageService],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  brands: RestaurantBrand[] = [];
  cities: City[] = [];
  selectedBrand?: RestaurantBrand;
  selectedCity?: City;
  locations: Location[] = [];
  selectedLocation?: Location;
  unavailableItems: UnavailableItem[]=[];
  errorMessage:string = '';
  search: string='';

  constructor(private http: HttpClient, private messageService: MessageService) {}

  ngOnInit() {
    this.fetchBrands();
  }

  fetchBrands() {
    this.http.get<RestaurantBrand[]>('http://localhost:8080/api/restaurant-brands/names')
      .subscribe((data) => {
        console.log('Fetched Brands:', data);
        this.brands = data;
      }
    );
  }
  onBrandChange() {
    if (this.selectedBrand) {
      this.http.get<City[]>(`http://localhost:8080/api/restaurant-brands/${this.selectedBrand.id}/city-names`)
        .subscribe((data) => {
          console.log('Fetched Cities:', data);
          this.cities = data;
          this.selectedCity = undefined; // Reset city selection when brand changes
        });
    } else {
      this.cities = [];
      this.selectedCity = undefined;
    }
  }
  onCityChange() {
    if (this.selectedCity && this.selectedBrand) {
      this.http.get<Location[]>(`http://localhost:8080/api/restaurant-brands/${this.selectedBrand?.id}/${this.selectedCity.id}/locations`)
        .subscribe((data) => {
          console.log('Fetched Locations:', data);
          this.locations = data;
          this.selectedLocation = undefined; // Reset location selection when city changes
          
        });
    } else {
      this.locations = [];
      this.selectedLocation = undefined;
      
    }
  }

  fetchUnavailableItems() {
    if (!this.selectedBrand || !this.selectedCity || !this.selectedLocation) {
      this.errorMessage = "Please select a Brand, City, and Location before submitting.";
      this.unavailableItems = [];
      return;
    }
  
    this.errorMessage = ""; // Clear previous error messages
  
    this.http.get<UnavailableItem[]>(`http://localhost:8080/api/restaurant-brands/${this.selectedBrand.id}/${this.selectedCity.id}/${this.selectedLocation.id}/unavailable-items`)
      .subscribe((data) => {
        console.log('Fetched Unavailable Items:', data);
        
        // Filter unavailable items
        this.unavailableItems = data.filter(item => !item.isAvailable);
  
        // Show message if no unavailable items are found
        if (this.unavailableItems.length === 0) {
          this.errorMessage = "No unavailable items found.";
        }
      }, (error) => {
        console.error('Error fetching unavailable items:', error);
        this.errorMessage = "Failed to fetch unavailable items. Please try again.";
        this.unavailableItems = []; // Clear the list in case of error
      });
  }
  
  // searchItems(){

  // }

  title = 'restaurant-app-ui';
}
