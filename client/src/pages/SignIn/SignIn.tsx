import { Card, Button, Form, Input } from 'antd';
import { Link } from 'react-router-dom';
import styles from './index.module.css';

const onFinish = (values) => {
  console.log('Success:', values);
};

const onFinishFailed = (errorInfo) => {
  console.log('Failed:', errorInfo);
};

function SignIn() {
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
        onFinish={(e) => onFinish}
        onFinishFailed={(e) => onFinishFailed}
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
}

export default SignIn;
