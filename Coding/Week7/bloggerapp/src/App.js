
import React from 'react';
import './App.css';
import { books, blogs, courses } from './data.js'; 

function App() {

  const bookdet = (
    <ul>
      {books.map((book) => (
        <div key={book.id}>
          <h3>{book.bname}</h3>
          <h4>{book.price}</h4>
        </div>
      ))}
    </ul>
  );

  const content = (
    <ul>
      {blogs.map((blog) => (
        <div key={blog.id}>
          <h3>{blog.title}</h3>
          <h4>{blog.author}</h4>
          <p>{blog.content}</p>
        </div>
      ))}
    </ul>
  );

  const coursedet = (
    <ul>
      {courses.map((course) => (
        <div key={course.id}>
          <h3>{course.name}</h3>
          <h4>{course.date}</h4>
        </div>
      ))}
    </ul>
  );


  return (
    <div className="container">
      {}
      <div className="column">
        <h1>Course Details</h1>
        {coursedet}
      </div>

      {}
      <div className="column">
        <h1>Book Details</h1>
        {bookdet}
      </div>

      {}
      <div className="column no-border">
        <h1>Blog Details</h1>
        {content}
      </div>
    </div>
  );
}

export default App;