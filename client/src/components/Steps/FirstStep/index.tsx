import React, { useState } from 'react';
import { Card, Form, Input, Button, DatePicker, message } from 'antd';
import { Formik } from 'formik';
import styles from './index.module.css';
import { sendPassportData } from '../../../api/passportApi';

const FirstStep: React.FC<{ setShowNextButton: Function }> = ({ setShowNextButton }) => {
  const passportSeriesRegex = /^[0-9]{4}$/;
  const passportNumberRegex = /^[0-9]{6}$/;

  const [isSubmitting, setIsSubmitting] = useState(false);

  const handleSubmit = async (values: any) => {
    setShowNextButton(true);
    try {
      setIsSubmitting(true);

      const { passportSeries, passportNumber, firstName, lastName, dateOfBirth } = values;
      const passportCombined = passportSeries + passportNumber;
      const token = localStorage.getItem('jwt_token'); // Получаем токен из локального хранилища
      if (!token) {
        throw new Error('JWT token is missing');
      }
      await sendPassportData(passportCombined, firstName, lastName, dateOfBirth, token);
      message.success('Passport data sent successfully');
      setShowNextButton(true);
    } catch (error) {
      message.error('Failed to send passport data');
      console.error('Error:', error);
    } finally {
      setIsSubmitting(false);
      setShowNextButton(true);
    }
  };

  return (
    <Formik
      initialValues={{
        passportSeries: '',
        passportNumber: '',
        passportIssuedBy: '',
        passportIssuedDate: null,
        passportDivisionCode: '',
        firstName: '',
        lastName: '',
        dateOfBirth: '',
      }}
      validate={(values) => {
        const errors: Record<string, string> = {};
        if (!passportSeriesRegex.test(values.passportSeries)) {
          errors.passportSeries = 'Серия паспорта должна содержать 4 цифры';
        }
        if (!passportNumberRegex.test(values.passportNumber)) {
          errors.passportNumber = 'Номер паспорта должен содержать 6 цифр';
        }
        return errors;
      }}
      onSubmit={handleSubmit}>
      {({ values, handleChange, handleSubmit, isValid }) => (
        <Form
          style={{ width: '100%', boxSizing: 'border-box' }}
          onSubmit={(e) => {
            setShowNextButton(true);
            handleSubmit(e);
          }}>
          <Card title="Заполнение паспортных данных" className={styles.card}>
            <Form.Item
              label="Серия паспорта"
              validateStatus={
                values.passportSeries && !passportSeriesRegex.test(values.passportSeries)
                  ? 'error'
                  : ''
              }
              help={
                values.passportSeries && !passportSeriesRegex.test(values.passportSeries)
                  ? 'Серия паспорта должна содержать 4 цифры'
                  : ''
              }>
              <Input
                name="passportSeries"
                value={values.passportSeries}
                onChange={handleChange}
                placeholder="1234"
              />
            </Form.Item>
            <Form.Item
              label="Номер паспорта"
              validateStatus={
                values.passportNumber && !passportNumberRegex.test(values.passportNumber)
                  ? 'error'
                  : ''
              }
              help={
                values.passportNumber && !passportNumberRegex.test(values.passportNumber)
                  ? 'Номер паспорта должен содержать 6 цифр'
                  : ''
              }>
              <Input
                name="passportNumber"
                value={values.passportNumber}
                onChange={handleChange}
                placeholder="567890"
              />
            </Form.Item>
            <Form.Item label="Дата выдачи">
              <DatePicker
                name="passportIssuedDate"
                value={values.passportIssuedDate}
                onChange={(date) =>
                  handleChange({ target: { name: 'passportIssuedDate', value: date } })
                }
              />
            </Form.Item>
            <Form.Item label="Кем выдан">
              <Input
                name="passportIssuedBy"
                value={values.passportIssuedBy}
                onChange={handleChange}
                placeholder="Наименование органа"
              />
            </Form.Item>
            <Form.Item label="Код подразделения">
              <Input
                name="passportDivisionCode"
                value={values.passportDivisionCode}
                onChange={handleChange}
                placeholder="XXXXXX"
              />
            </Form.Item>
            <Form.Item label="Имя">
              <Input
                name="firstName"
                value={values.firstName}
                onChange={handleChange}
                placeholder="Иван"
              />
            </Form.Item>
            <Form.Item label="Фамилия">
              <Input
                name="lastName"
                value={values.lastName}
                onChange={handleChange}
                placeholder="Иванов"
              />
            </Form.Item>
            <Form.Item label="Дата рождения">
              <DatePicker
                name="dateOfBirth"
                value={values.dateOfBirth}
                onChange={(date) => handleChange({ target: { name: 'dateOfBirth', value: date } })}
              />
            </Form.Item>
          </Card>
          <div style={{ marginTop: 16, textAlign: 'center', marginBottom: '10px' }}>
            <Button
              type="primary"
              htmlType="submit"
              disabled={
                !isValid ||
                isSubmitting ||
                !values.passportSeries ||
                !values.passportNumber ||
                isSubmitting
              }>
              {isSubmitting ? 'Отправка...' : 'Отправить'}
            </Button>
          </div>
        </Form>
      )}
    </Formik>
  );
};

export default FirstStep;
