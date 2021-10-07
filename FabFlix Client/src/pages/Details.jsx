import React, { Component } from "react";
import Axios from 'axios';
import Search from "../services/Search";
import Billing from "../services/Billing";

const localStorage = require("local-storage");

class Details extends Component {
  state = {
    quantity: 1,
    movie_id: localStorage.get("movie_id"),
    title: null,
    year: null,
    director: null,
    rating: null,
    num_votes: null,
    budget: null,
    revenue: null,
    overview: null,
    backdrop_path: null,
    poster_path: null,
    genres: "",
    people: "",
    message: ""
  };

  handleSubmit = e => {
    e.preventDefault();
    const { movie_id, quantity } = this.state;
    const { common } = Axios.defaults.headers;
    common["email"] = localStorage.get('email');
    common["session_id"] = localStorage.get('session_id');
    Billing.cartInsert(common["email"], movie_id, quantity)
    .then(response => {
      console.log(response);
      this.setState({message: response["data"]["message"]})
    })
    .catch(error => console.log(error));
  };

  updateField = e => {
    const { name, value } = e.target;

    this.setState({ [name]: value });
  };

componentDidMount() {
    const { movie_id } = this.state;
    const { common } = Axios.defaults.headers;
    common["email"] = localStorage.get('email');
    common["session_id"] = localStorage.get('session_id');
    Search.movieGet(movie_id)
    .then(response => {
      if(response["data"]["resultCode"] === 210){
        console.log(response);
        this.setState({title: response["data"]["movie"]["title"]});
        this.setState({year: response["data"]["movie"]["year"]});
        this.setState({director: response["data"]["movie"]["director"]});
        this.setState({rating: response["data"]["movie"]["rating"]});
        this.setState({num_votes: response["data"]["movie"]["num_votes"]});
        this.setState({budget: response["data"]["movie"]["budget"]});
        this.setState({revenue: response["data"]["movie"]["revenue"]});
        this.setState({overview: response["data"]["movie"]["overview"]});
        this.setState({backdrop_path: "https://image.tmdb.org/t/p/original" + response["data"]["movie"]["backdrop_path"]});
        this.setState({poster_path: "https://image.tmdb.org/t/p/w500" + response["data"]["movie"]["poster_path"]});
        const genres = [];
        for(var i = 0; i < response["data"]["movie"]["genres"].length; i++){
          genres.push(response["data"]["movie"]["genres"][i]["name"]);
        }
        this.setState({genres: genres.join(", ")});
        const people = [];
        for(i = 0; i < response["data"]["movie"]["people"].length; i++){
          people.push(response["data"]["movie"]["people"][i]["name"]);
        }
        this.setState({people: people.join(", ")});
      }
      else{
        this.setState({message: response["data"]["message"]});
      }
    })
    .catch(error => console.log(error));
  }

  render() {
    const {title, year, director, rating, num_votes, budget, revenue, overview, poster_path, backdrop_path, genres, people, message } = this.state;
    const sectionStyle = {
      backgroundImage: `url(${backdrop_path})`,
    };

    return (
    <div className="flex-container">
      <div className="detail-container" style={sectionStyle}></div>
      <div className="movie-info-container">
        <img src={poster_path} alt={title}></img>
        <div>
          <h1 className="movie-text-title">{title}</h1>
          <div className="movie-text-container">
            <div>
              <label>Title:</label>
              <p>{title}</p>
              <br></br>
            </div>
            <div>
              <label>Director:</label>
              <p>{director}</p>
              <br></br>
            </div>
            <div>
              <label>Year:</label>
              <p>{year}</p>
              <br></br>
            </div>
            <div>
              <label>Rating:</label>
              <p>{rating}</p>
              <br></br>
            </div>
            <div>
              <label>Votes:</label>
              <p>{num_votes}</p>
              <br></br>
            </div>
            {budget !== "0" && budget !== null &&
              <div>
                <label>Budget:</label>
                <p>{budget}</p>
                <br></br>
              </div>
            }
            {revenue !== "0" && revenue !== null &&
              <div>
                <label>Revenue:</label>
                <p>{revenue}</p>
                <br></br>
              </div>
            }
            {overview !== "" && overview !== null &&
              <div>
                <label>Overview:</label>
                <p>{overview}</p>
                <br></br>
              </div>
            }
            <div>
              <label>Genres:</label>
              <p>{genres}</p>
              <br></br>
            </div>
            <div>
              <label>People:</label>
              <p>{people}</p>
              <br></br>
            </div>
          </div>
          <div className="flex-container">
            <form onSubmit={this.handleSubmit}>
              <input type="number" className="input-field" name="quantity" min="1" defaultValue="1" onChange={this.updateField} required/>
              <button className="form-button">Add to Cart</button>
            </form>
            <label>{message}</label>
          </div>
        </div>
      </div>
    </div>
    );
  }
}

export default Details;
