import { Card, Button, Form, Input } from 'antd';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import styles from './index.module.css';
import { APIHOST } from '../../utils/consts';
import { observer } from 'mobx-react-lite';

const SignUp = observer(() => {
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
        // Обработка успешной регистрации
        console.log('Регистрация прошла успешно');
        navigate('/signIn'); // Перенаправление на страницу входа
      } else {
        // Обработка временного неудачного ответа
        console.error('Регистрация не удалась');
      }
    } catch (error) {
      console.error('Ошибка:', error);
    }
    navigate('/signIn');
  };

  const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };

  return (
    <Card style={{ width: 480, margin: 'auto', textAlign: 'center' }}>
      <div className={styles.topModule}>
        <div className={styles.logo} />
        <span>Сервис Командировок</span>
      </div>

      <Form
        name="normal_register"
        className="register-form"
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
        <Form.Item
          name="confirmPassword"
          dependencies={['password']}
          hasFeedback
          rules={[
            { required: true, message: 'Пожалуйста, подтвердите ваш пароль' },
            ({ getFieldValue }) => ({
              validator(_, value) {
                if (!value || getFieldValue('password') === value) {
                  return Promise.resolve();
                }
                return Promise.reject('Пароли не совпадают');
              },
            }),
          ]}
          style={{ width: '100%' }}>
          <Input type="password" placeholder="Подтвердите пароль" />
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit" style={{ width: '100%' }}>
            Зарегистрироваться
          </Button>
          <div style={{ marginTop: '10px' }}>
            <Link to="/signIn">Уже есть аккаунт? Войти</Link>
          </div>
        </Form.Item>
      </Form>
    </Card>
  );
});

export default SignUp;
