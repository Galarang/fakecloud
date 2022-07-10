import { Component } from '@angular/core';
import { DataAccessService } from './data-access.service';
import { RequestObject } from './model/request-object';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'swiss-ui';

  transportationTypes: string[] = ['TRAIN', 'BUS', 'SHIP', 'TRAM', 'CABLEWAY'];
  errorMessage: any = undefined;
  message: string = '';

  requestObject: RequestObject = {
    locationFrom: '',
    locationTo: '',
    transportation: '',
    via: []
  };

  viaField: string = '';

  constructor(private dataAccess: DataAccessService){}

  onSubmit(): void {
    this.errorMessage = null;
    this.dataAccess.getDuration(this.requestObject).subscribe((response: any) => {
      this.message = response?.duration
    }, (error: any) => {
      this.errorMessage = error;
    })
  }

  initRequestObject(): void {
    this.requestObject = {
      locationFrom: '',
      locationTo: '',
      transportation: '',
      via: []
    };
  }

  isViaEmptyOrContainsDuplicate(): boolean {
    return this.viaField.length === 0 || this.requestObject.via.some(via => JSON.stringify(via) === JSON.stringify(this.viaField));
  }

  checkStopsAreFull(): boolean {
    return this.requestObject.via.length === 5;
  }

  addStopToViaList() {
    this.requestObject.via.push(this.viaField);
    this.viaField = '';
  }

  containsStop(): boolean {
    return this.requestObject.via.length > 0;
  }
deleteSelectedStops(selectedStops: any){
  const stopsForDeletion = selectedStops.map((stop: any) => stop._value)
  this.requestObject.via = this.requestObject.via.filter( ( el ) => !stopsForDeletion.includes( el ) );
}
}
