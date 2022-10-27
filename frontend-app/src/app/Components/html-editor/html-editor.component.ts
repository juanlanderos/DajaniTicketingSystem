import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AngularEditorConfig } from '@kolkov/angular-editor';

@Component({
  selector: 'app-html-editor',
  templateUrl: './html-editor.component.html',
  styleUrls: ['./html-editor.component.css']
})
export class HtmlEditorComponent {
	@Input() htmlContent: any; //used for interactive user input in html editor
	@Input() placeholder = "Enter text here"; //used in tv html file for placeholder text vars
	@Output() signals = new EventEmitter(); //defaults to "undefined" if there is no reponse. 
	
	config: AngularEditorConfig;

	/* Criteria set for the html box */
	constructor() {
		this.config = {
			editable: true, //can edit fields in html editor
			spellcheck: true, //allows spell check in html editor
			height: '10rem', //How tall html box will be
			minHeight: '5rem', //sets min height of box
			placeholder: 'Enter text here...', //used in tv html as placeholder text for var
			translate: 'no',
			defaultParagraphSeparator: 'p',
			defaultFontName: 'Arial', //defaults to arial in html editor
		};
	}
	//emit html editor values to the parent comment as user types;
	onValueChange(ev: any): void {
		this.signals.emit(ev);
	}
}
