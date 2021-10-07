import React, { Component } from "react";
import Axios from 'axios';
import Search from "../services/Search";
//import Idm from "../services/Idm";

const localStorage = require("local-storage");

class Movies extends Component {
  state = {
    searchTerm: "",
    filter: "title",
    movies: [],
    message: "",
    title: "",
    year: "",
    director: "",
    genre: "",
    orderBy: "title",
    direction: "asc",
    limit: 10,
    offset: 0,
    page: 0,
    search: false,
    advMenu: false,
    movie: null
  };

  handleSubmit = (page, offset) => {
    //e.preventDefault();
    //console.log("Search Term: " + searchTerm);
    //console.log("Filter: " + filter);
    
    const { searchTerm, filter, title, year, director, genre, orderBy, limit, direction } = this.state;
    const { common } = Axios.defaults.headers;
    common["email"] = localStorage.get('email');
    common["session_id"] = localStorage.get('session_id');
    //common["transaction_id"] = localStorage.get('transaction_id');
    // Idm.session(common["email"], common["session_id"])
    // .then(response => {
    //   if(response["data"]["resultCode"] !== 130){
    //     handleLogOut();
    //     //console.log(this.state.movies);
    //   }
    //   else{
    //     localStorage.set('session_id', response["data"]["session_id"])
    //     common["session_id"]=response["data"]["session_id"]
    //   }
    // })
    // .catch(error => console.log(error));
    
    if(filter !== "keyword"){
      var path = "?" + filter + "=" + searchTerm;
      if(title !== ""){
        path += "&title=" + title;
      }
      if(year !== ""){
        path += "&year=" + year;
      }
      if(director !== ""){
        path += "&director=" + director;
      }
      if(genre !== ""){
        path += "&genre=" + genre;
      }
      path += "&limit=" + limit + "&offset=" + offset + "&orderby=" + orderBy + "&direction=" + direction;
      Search.movieSearch(path)
        .then(response => {
          if(response["data"]["resultCode"] === 210){
            console.log(response);
            this.setState({
              movies: response["data"]["movies"],
              search: true,
              offset: offset,
              page: page
            });
            //console.log(this.state.movies);
          }
          else{
            this.setState({
              message: response["data"]["message"],
              offset: offset,
              page: page
            });
          }
        })
        .catch(error => console.log(error));
    }
    else{
      Search.movieBrowse(searchTerm)
        .then(response => {
          if(response["data"]["resultCode"] === 210){
            console.log(response);
            this.setState({
              movies: response["data"]["movies"],
              search: true
            });
            //console.log(this.state.movies);
          }
          else{
            this.setState({message: response["data"]["message"]});
          }
        })
        .catch(error => console.log(error));
    }
  };

  
  updateField = ({target}) => {
    const { name, value } = target;

    this.setState({ [name]: value });
  };

  newSearch = e => {
    e.preventDefault();
    this.handleSubmit(0, 0);
  }

  nextPage = e => {
    e.preventDefault();
    const { page, limit } = this.state;
    this.handleSubmit(page + 1, (page + 1) * limit);
  }

  prevPage = e => {
    e.preventDefault();
    const { page, limit } = this.state;
    this.handleSubmit(page - 1, (page - 1) * limit);
  }

  movieDetails = (movie_id) => {
    console.log(movie_id);
    localStorage.set("movie_id", movie_id);
    this.props.history.push("/details/" + movie_id);
  }
  
  reset = () => {
    this.setState({
      searchTerm: "",
      movies: [],
      message: "",
      title: "",
      year: "",
      director: "",
      genre: "",
      orderBy: "title",
      direction: "asc",
      movie: null
    })
  }

  render() {
    const { searchTerm, filter, movies, search, title, year, director, genre, limit, advMenu, page, message }  = this.state;
    const movieItems = movies.map((item, key) =>
      <div className="movie-item" key = {item.movie_id} onClick = {() => this.movieDetails(item.movie_id)}>
        <img src = {"https://image.tmdb.org/t/p/w185" + item.poster_path} alt = {item.title}></img>
        <label className="title">{item.title}</label>
        <label>{item.year}</label>
        <label>{item.director}</label>
        <label>Rating: {item.rating}</label>
      </div>
    );
    return (
      <div>
        <div className="search-container">
          <form onSubmit={this.newSearch}>
              <input
                className="search-field"
                type="search"
                placeholder="Search..."
                name="searchTerm"
                value={searchTerm}
                onChange={this.updateField}
              />
              <select className="search-filter" id = "filter" name="filter" defaultValue="title" onChange={this.updateField}>
                <option value="title">Title</option>
                <option value="year">Year</option>
                <option value="director">Director</option>
                <option value="keyword">Keyword</option>
              </select>
              <button className="search-submit">Search</button>
          </form>
          <button className = "form-button" onClick={() => this.setState({advMenu: !advMenu})}>Show/Hide Menu</button>
          <button className = "form-button" onClick={() => this.reset()}>Clear Fields</button>
        </div>
        <div>
          { advMenu &&
          <form className="advMenu">
            {filter !== "title" && filter !== "keyword" &&
              <div className="advMenu-item">
                <label>Title: </label>
                <input
                  className="advMenu-field"
                  type="search"
                  placeholder="Avengers: Endgame"
                  name="title"
                  value={title}
                  onChange={this.updateField}
                />
              </div>
            }
            {filter !== "year" && filter !== "keyword" &&
              
              <div className="advMenu-item">
                <label>Year: </label>
                <input
                className="advMenu-field"
                type="search"
                placeholder="e.g. 2019"
                name="year"
                value={year}
                onChange={this.updateField}
                />
              </div>
            }
            {filter !== "director" && filter !== "keyword" &&
              <div className="advMenu-item">
                <label>Director: </label>
                <input
                  className="advMenu-field"
                  type="search"
                  placeholder="e.g. Joe Russo"
                  name="director"
                  value={director}
                  onChange={this.updateField}
                />
              </div>
            }
            {filter !== "genre" && filter !== "keyword" &&
              <div className="advMenu-item">
                <label>Genre: </label>
                <input
                  className="advMenu-field"
                  type="search"
                  placeholder="e.g. Action"
                  name="genre"
                  value={genre}
                  onChange={this.updateField}
                />
              </div>
            }
            <div className="advMenu-item">
            <label>Order By: </label>
            <select className="advMenu-filter" id = "orderBy" name="orderBy" defaultValue="title" onChange={this.updateField}>
                <option value="title">Title</option>
                <option value="rating">Rating</option>
                <option value="year">year</option>
            </select>
            </div>
            <div className="advMenu-item">
            <label>Direction: </label>
            <select className="advMenu-filter" id = "direction" name="direction" defaultValue="asc" onChange={this.updateField}>
                <option value="asc">Ascending</option>
                <option value="desc">Descending</option>
            </select>
            </div>
            <div className="advMenu-item">
            <label>Results: </label>
            <select className="advMenu-filter" id = "limit" name="limit" defaultValue={10} onChange={this.updateField}>
                <option value={10}>10</option>
                <option value={25}>25</option>
                <option value={50}>50</option>
                <option value={100}>100</option>
            </select>
            </div>
          </form>
          }
        </div>
        <div className = "flex-container"> 
          <label className="form-message">{message}</label>
        </div>
        <div className = "movie-list">
          {movieItems}
        </div>
        <div className = "button-container">
          { search && page !== 0 &&
            <button className = "form-button" onClick={this.prevPage}>Prev</button>
          }
          { search && movies.length === limit && 
            <button className = "form-button" onClick={this.nextPage}>Next</button>
          }
        </div>
      </div>
    );
  }
}

export default Movies;
