import logo from './logo.svg';
import './App.css';
import { useState } from 'react';

function App() {

  const [tekst, setTekst] = useState("Tekst");

  const handleKlik = () => {
    setTekst("Kliknuo si");
  };

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          {4 === 3 ? "bla" : "blalala"}
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
        <button onClick={handleKlik}>KLIK</button>
      </header>
    </div>
  );
}

export default App;
