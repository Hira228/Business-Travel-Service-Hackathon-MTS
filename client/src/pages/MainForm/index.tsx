import React, { useEffect, useState } from 'react';
import { Button, message, Steps, theme } from 'antd';
import { LoadingOutlined } from '@ant-design/icons';
import styles from './MainForm.module.css';
import { FirstStep, SecondStep, FourthStep, FifthStep, ThirdStep } from '../../components/Steps';

const MainForm: React.FC = () => {
  const { token } = theme.useToken();
  const [current, setCurrent] = useState(0);
  const [showNextButton, setShowNextButton] = useState(true);

  useEffect(() => {
    setCurrent(0);
  }, []);
  const next = () => {
    setCurrent(current + 1);
    console.log('gone');
  };

  const prev = () => {
    setCurrent(current - 1);
  };
  console.log(current);
  const stepsList = [
    {
      title: 'Назначение командировки',
      content: () => (
        <>
          <p>Подтвердите данные</p>
          <FirstStep setShowNextButton={setShowNextButton} />
        </>
      ),
    },
    {
      title: 'Бронирование услуг',
      content: () => (
        <>
          <p>Забронируйте услуги</p>

          <SecondStep />
        </>
      ),
    },
    {
      title: 'Согласование командировки',
      content: () => <ThirdStep />,
    },
    {
      title: 'Авансовые средства',
      content: () => (
        <>
          <p>Зачисление авансовых средств</p>
          <FourthStep />
          <Button onClick={() => setShowNextButton(true)}>Change State</Button>
        </>
      ),
    },
    {
      title: 'Приказ о командировке',
      content: () => (
        <>
          <p>Дождитесь выпуска приказа</p>

          <FifthStep />
        </>
      ),
    },
    {
      title: 'Отчётные документы',
      content: () => (
        <>
          <p>Заполните и предоставьте необходимые документы</p>

          <FourthStep />
        </>
      ),
    },
  ];

  const renderTitle = (title: string, index: number) => {
    if (current === index) {
      return <span>{title}</span>;
    }
    return (
      <span
        style={{ opacity: 0 }}
        onMouseEnter={() => setCurrent(index)}
        onMouseLeave={() => setCurrent(current)}>
        {title}
      </span>
    );
  };

  const items = stepsList.map((item, index) => ({
    key: item.title,
    title: renderTitle(item.title, index),
    icon: current === index ? <LoadingOutlined /> : undefined,
  }));

  const contentStyle: React.CSSProperties = {
    color: token.colorTextTertiary,
    backgroundColor: token.colorFillAlter,
    lineHeight: '60px',
    textAlign: 'center',
    borderRadius: token.borderRadiusLG,
    border: `1px dashed ${token.colorBorder}`,
    marginTop: 16,
  };

  return (
    <div className={styles.container}>
      <Steps current={current} items={items} direction="horizontal" />
      <div className={`${styles.content}`} style={contentStyle}>
        {stepsList[current].content()}
      </div>
      <div style={{ marginTop: 24, textAlign: 'left' }}>
        {current > 0 && (
          <Button style={{ margin: '0 8px' }} onClick={() => prev()}>
            Previous
          </Button>
        )}
      </div>
      <div style={{ marginTop: -24, textAlign: 'right' }}>
        {showNextButton && current < stepsList.length - 1 && (
          <Button type="primary" onClick={() => next()}>
            Next
          </Button>
        )}
        {current === stepsList.length - 1 && (
          <Button type="primary" onClick={() => message.success('Processing complete!')}>
            Done
          </Button>
        )}
      </div>
    </div>
  );
};

export default MainForm;
