import { Card, Button, Form, Input } from 'antd';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import styles from './index.module.css';
import { useContext } from 'react';
import { observer } from 'mobx-react-lite';
import { APIHOST } from '../../utils/consts';
import { Context } from '../../main';

const SignIn = observer(() => {
  const { user } = useContext(Context);
  const navigate = useNavigate();

  const onFinish = async (values) => {
    try {
      const response = await fetch(`${APIHOST}/auth/signUp`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(values),
      });

      if (response.ok) {
        const data = await response.json();
        const token = data.token;

        // Сохранение JWT токена в локальном хранилище
        localStorage.setItem('token', token);

        // Обработка успешного ответа
        console.log('Вход успешен');
        user.setIsAuth(true);
        navigate('/jorney');

        // Запрос на сервер для получения данных пользователя
        const userResponse = await fetch(`${APIHOST}/user`, {
          method: 'GET',
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        if (userResponse.ok) {
          const userData = await userResponse.json();
          user.setUser(userData); // Сохранение данных пользователя в объект user
        } else {
          console.error('Не удалось получить данные пользователя');
        }
      } else {
        // Обработка временного неудачного ответа
        console.error('Вход не удался');
      }
    } catch (error) {
      console.error('Ошибка:', error);
    }

    // test
    user.setIsAuth(true);
    navigate('/jorney');
  };

  const onFinishFailed = (errorInfo) => {
    console.log('Ошибка:', errorInfo);
  };

  return (
    <Card style={{ width: 480, margin: 'auto', textAlign: 'center' }}>
      <div className={styles.topModule}>
        <div className={styles.logo} />
        <span>Сервис Командировок</span>
      </div>

      <Form
        name="normal_login"
        className="login-form"
        initialValues={{ remember: true }}
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
        style={{ width: '80%', margin: 'auto' }}>
        <Form.Item
          name="login"
          rules={[{ required: true, message: 'Пожалуйста, введите ваш логин' }]}
          style={{ width: '100%' }}>
          <Input placeholder="Логин" />
        </Form.Item>
        <Form.Item
          name="password"
          rules={[{ required: true, message: 'Пожалуйста, введите ваш пароль' }]}
          style={{ width: '100%' }}>
          <Input type="password" placeholder="Пароль" />
        </Form.Item>
        <Form.Item>
          <Button type="link" className="login-form-forgot">
            Восстановить
          </Button>
        </Form.Item>

        <Form.Item>
          <Button type="primary" htmlType="submit" style={{ width: '100%' }}>
            Войти
          </Button>
          <div style={{ marginTop: '10px' }}>
            <Link to="/signUp">Зарегистрироваться</Link>
          </div>
        </Form.Item>
      </Form>
    </Card>
  );
});
export default SignIn;
