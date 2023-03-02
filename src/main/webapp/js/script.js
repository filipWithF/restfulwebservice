const table = document.querySelector('table');
const uploadButton = document.querySelector('#uploadButton');
const deleteButton = document.querySelector('#deleteButton');
const title = document.querySelector('#title');
const author = document.querySelector('#author');
const topic = document.querySelector('#topic');
const pubDate = document.querySelector('#publishedDate');
const id = document.querySelector('#id');

const request = new XMLHttpRequest();

var books = [];

uploadButton.addEventListener('click', uploadBook);
deleteButton.addEventListener('click', deleteBook);

function load() {
	
	request.open('GET', '/api/v1/books', true);
	
	request.onload = function() {
		
		if(request.status >= 200 && request.status < 400) {
			
			var data = JSON.parse(this.response);
			
			books = Array.from(data);
			
			console.log( books );

			let real_data = Object.keys(books[0]);
			generateTableHead(table, real_data);
			generateTable(table, books);
		} else {
			console.log('Ups something went wrong.');
		}
	}
	
	request.send();
}

load();

function uploadBook() {
	
	var book = JSON.stringify({
	  	title: title.value,
	  	author: author.value,
	  	topic: topic.value,
	  	publishedDate: pubDate.value
	  });
	  
	  request.open('POST', '/api/v1/books', true);
	
	  request.setRequestHeader('Content-Type', 'application/json');
	  
	  request.onload = () => {
		  
		  let row = table.insertRow();
			
		  let cellTitle = row.insertCell();
		  let cellAuthor = row.insertCell();
		  let cellTopic = row.insertCell();
		  let cellPubDate = row.insertCell();
			
		  let bookTitle = document.createTextNode(title.value);
		  let bookAuthor = document.createTextNode(author.value);
		  let bookTopic = document.createTextNode(topic.value);
	      let bookPubDate = document.createTextNode(pubDate.value);

		  cellTitle.appendChild(bookTitle);
		  cellAuthor.appendChild(bookAuthor);
		  cellTopic.appendChild(bookTopic);
		  cellPubDate.appendChild(bookPubDate);
		  
		  window.location.reload(true);
	  }
	  
	  request.send(book);	  
} 

function deleteBook() {
	
	request.open('DELETE', '/api/v1/books/' + id.value, true);
	request.onload = () => {
		location.reload(true);
	}
	
	request.send(null);
}

function generateTableHead(table, data) {
	  let thead = table.createTHead();
	  let row = thead.insertRow();
	  
	  for (let key of data) {
	    let th = document.createElement("th");
	    let text = document.createTextNode(key);
	    th.appendChild(text);
	    row.appendChild(th);
	  }
}

function generateTable(table, data) {
	  for (let element of data) {
	    let row = table.insertRow();
	    for (key in element) {
	      let cell = row.insertCell();
	      let text = document.createTextNode(element[key]);
	      cell.appendChild(text);
	    }
	  }
}
