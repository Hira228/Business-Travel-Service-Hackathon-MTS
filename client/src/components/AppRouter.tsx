import { Routes, Route } from 'react-router-dom';
import { authRoutes, publicRoutes } from '../routes';
import { useContext } from 'react';
import { Context } from '../main';
function AppRouter() {
  const { user } = useContext(Context);
  console.table(user.getIsAuth());
  return (
    <Routes>
      {user.isAuth &&
        authRoutes.map(({ path, Component }) => (
          <Route key={path} path={path} Component={Component} />
        ))}
      {publicRoutes.map(({ path, Component }) => (
        <Route key={path} path={path} Component={Component} />
      ))}
    </Routes>
  );
}

export default AppRouter;
