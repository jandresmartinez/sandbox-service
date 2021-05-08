import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Quote } from '../interface/Quote';

@Injectable({
  providedIn: 'root'
})
export class QuoteService {

  quoteList!: Quote[];
  quote!: Quote;

  constructor() { }

  async fetchData(): Promise<void> {
    const quotesURL = 'https://gist.githubusercontent.com/camperbot/5a022b72e96c4c9585c32bf6a75f62d9/raw/e3c6895ce42069f0ee7e991229064f167fe8ccdc/quotes.json';
    const response = await fetch(quotesURL);
    const quotes = await response.json();
    const idx = Math.floor(Math.random() * quotes.quotes.length);
    const newQuote = quotes.quotes[idx];
    this.quoteList = quotes.quotes;
    this.quote = newQuote;
    
  }
  

 

  getNewQuote(): void {
    const idx = Math.floor(Math.random() * this.quoteList.length);
    const newQuote = this.quoteList[idx];
    this.quote = newQuote;
  }

  getQuote(): Quote {
    return this.quote;
  }

}
