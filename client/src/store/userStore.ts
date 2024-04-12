import { makeAutoObservable } from 'mobx';
export default class UserStore {
  private _isAuth: boolean;
  private _user:object
  constructor() {
    this._isAuth = false;
    this._user = {};
    makeAutoObservable(this);
  }
  setIsAuth(bool:boolean) {
    this._isAuth = bool;
    console.log(this._isAuth)  // to remove in future
  }
  setUser(user:object) {
    this._user = user;
  }
  getIsAuth() {
    return this._isAuth;
  }
  getUser() {
    return this._user;
  }
}