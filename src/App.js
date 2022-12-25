import './App.css';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

function App() {
  const [authorForm, setAuthorForm] = useState({
    firstName: '',
    lastName: '',
    books: []
  });
  const [authors, setAuthors] = useState([]);
  const [selectedAuthor, setSelectedAuthor] = useState(null);

  const [bookForm, setBookForm] = useState({
    isbn: '',
    name: ''
  });
  const [books, setBooks] = useState([]);
  const [selectedBook, setSelectedBook] = useState(null);

  useEffect(() => {
    axios.get('/authors')
      .then(res => setAuthors(res.data))
      .catch(err => console.error(err));
  }, []);

  useEffect(() => {
    axios.get(`/books`)
      .then(res => setBooks(res.data))
      .catch(err => console.error(err));
  }, []);

  const handleAuthorChange = (e) => {
    const { name, value } = e.target;
    setAuthorForm({ ...authorForm, [name]: value });
  }

  const handleAuthorSubmit = (e) => {
    e.preventDefault();
    axios.post(`/authors`, authorForm)
      .then(res => {
        setAuthors([...authors, res.data]);
        setAuthorForm({ firstName: '', lastName: '', books: [] });
      })
      .catch(err => console.error(err));
  }

  const handleAuthorSelect = (author) => {
    setSelectedAuthor(author);
    setAuthorForm({ firstName: author.firstName, lastName: author.lastName, books: author.books });
  }

  const handleAuthorUpdate = (e) => {
    e.preventDefault();
    axios.put(`/authors/${selectedAuthor.id}`, authorForm)
      .then(res => {
        const updatedAuthors = authors.map(author => author.id === selectedAuthor.id ? res.data : author);
        setAuthors(updatedAuthors);
        setSelectedAuthor(null);
        setAuthorForm({ firstName: '', lastName: '', books: [] });
      })
      .catch(err => console.error(err));
  }

  const handleAuthorDelete = (author) => {
    axios.delete(`/authors/${author.id}`)
      .then(() => {
        const updatedAuthors = authors.filter(a => a.id !== author.id);
        setAuthors(updatedAuthors);
      })
      .catch(err => console.error(err));
  }

  const handleBookChange = (e) => {
    const { name, value } = e.target;
    setBookForm({ ...bookForm, [name]:

value });
}

const handleBookSubmit = (e) => {
e.preventDefault();
axios.post(`/books`, bookForm)
.then(res => {
setBooks([...books, res.data]);
setBookForm({ isbn: '', name: '' });
})
.catch(err => console.error(err));
}

const handleBookSelect = (book) => {
setSelectedBook(book);
setBookForm({ isbn: book.isbn, name: book.name });
}

const handleBookUpdate = (e) => {
e.preventDefault();
axios.put(`/books/${selectedBook.id}`, bookForm)
.then(res => {
const updatedBooks = books.map(book => book.id === selectedBook.id ? res.data : book);
setBooks(updatedBooks);
setSelectedBook(null);
setBookForm({ isbn: '', name: '' });
})
.catch(err => console.error(err));
}

const handleBookDelete = (book) => {
axios.delete(`/books/${book.id}`)
.then(() => {
const updatedBooks = books.filter(b => b.id !== book.id);
setBooks(updatedBooks);
})
.catch(err => console.error(err));
}

return (
  <div>
  <h1>Authors</h1>
  <form onSubmit={handleAuthorSubmit}>
  <input type="text" name="firstName" placeholder="First Name" value={authorForm.firstName} onChange={handleAuthorChange} />
  <input type="text" name="lastName" placeholder="Last Name" value={authorForm.lastName} onChange={handleAuthorChange} />
  <button>Add Author</button>
  </form>
  <ul>
  {authors.map(author => (
  <li key={author.id} onClick={() => handleAuthorSelect(author)}>
  {author.firstName} {author.lastName}
  </li>
  ))}
  </ul>
  {selectedAuthor && (
  <form onSubmit={handleAuthorUpdate}>
  <input type="text" name="firstName" placeholder="First Name" value={authorForm.firstName} onChange={handleAuthorChange} />
  <input type="text" name="lastName" placeholder="Last Name" value={authorForm.lastName} onChange={handleAuthorChange} />
  <button>Update Author</button>
  <button onClick={() => handleAuthorDelete(selectedAuthor)}>Delete Author</button>
  <button onClick={() => setSelectedAuthor(null)}>Cancel</button>
  </form>
  )}
  <h1>Books</h1>
  <form onSubmit={handleBookSubmit}>
  <input type="text" name="isbn" placeholder="ISBN" value={bookForm.isbn} onChange={handleBookChange} />
  <input type="text" name="name" placeholder="Name" value={bookForm.name} onChange={handleBookChange}
  />
  <button>Add Book</button>
  
  </form>
  <ul>
  {books.map(book => (
  <li key={book.id} onClick={() => handleBookSelect(book)}>
  {book.isbn} {book.name}
  </li>
  ))}
  </ul>
  {selectedBook && (
  <form onSubmit={handleBookUpdate}>
  <input type="text" name="isbn" placeholder="ISBN" value={bookForm.isbn} onChange={handleBookChange} />
  <input type="text" name="name" placeholder="Name" value={bookForm.name} onChange={handleBookChange} />
  <button>Update Book</button>
  <button onClick={() => handleBookDelete(selectedBook)}>Delete Book</button>
  <button onClick={() => setSelectedBook(null)}>Cancel</button>
  </form>
  )}
  </div>
  );
  
}



export default App;