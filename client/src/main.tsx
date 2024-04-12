import { createContext } from 'react';
import ReactDOM from 'react-dom/client';
import AppRouter from './components/AppRouter';
import { BrowserRouter } from 'react-router-dom';
import UserStore from './store/userStore';
import './index.css';

// eslint-disable-next-line @typescript-eslint/no-explicit-any
export const Context = createContext<any | null>(null);

ReactDOM.createRoot(document.getElementById('root')!).render(
  <Context.Provider value={{ user: new UserStore() }}>
    <BrowserRouter>
      <AppRouter />
    </BrowserRouter>
    <div className="back" />
  </Context.Provider>,
);
