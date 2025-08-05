import React, { Component } from 'react';
import CurrencyConvertor from './CurrencyConvertor';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      counter: 0
    };
  }

  increment = () => {
    this.setState({ counter: this.state.counter + 1 });
  };

  decrement = () => {
    this.setState({ counter: this.state.counter - 1 });
  };

  sayHello = () => {
    alert("Hello! I have been Incremented.");
  };

  handleIncrementMultiple = () => {
    this.increment();
    this.sayHello();
  };

  sayMessage = (msg) => {
    alert(msg);
  };

  handleSyntheticEvent = (e) => {
    alert("I was clicked");
    console.log("Synthetic Event:", e);
  };

  render() {
    return (
      <div style={{ textAlign: "center", padding: "20px" }}>
        <h1>Event Examples App</h1>

        <h2>Counter: {this.state.counter}</h2>
        <button onClick={this.handleIncrementMultiple}>Increment</button>
        <button onClick={this.decrement}>Decrement</button>

        <br /><br />
        <button onClick={() => this.sayMessage("Welcome")}>Say Welcome</button>

        <br /><br />
        <button onClick={this.handleSyntheticEvent}>OnPress</button>

        <br /><br />
        <CurrencyConvertor />
      </div>
    );
  }
}

export default App;
