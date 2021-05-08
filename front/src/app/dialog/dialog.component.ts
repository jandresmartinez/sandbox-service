import { Component, OnInit,Inject } from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Quote } from '../interface/Quote';
import { QuoteService } from '../service/quote.service';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {

  quote!: Quote;
  constructor(@Inject(MAT_DIALOG_DATA) public data: {name: string},public quoteService: QuoteService) { }

  ngOnInit() {
    this.getNewQuote();
  }

  async getNewQuote() {
    await this.quoteService.fetchData();
    this.quoteService.getNewQuote();
    this.quote = this.quoteService.getQuote();  
    
  }

}
