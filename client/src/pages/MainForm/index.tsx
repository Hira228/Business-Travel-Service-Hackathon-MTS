import React, { useState } from 'react';
import { Button, message, Steps, theme } from 'antd';
import { LoadingOutlined } from '@ant-design/icons';
import styles from './MainForm.module.css';
import FirstStep from '../../components/Steps/FirstStep';
import ThirdStep from '../../components/Steps/ThirdStep';
// Импортируйте остальные компоненты шагов

const MainForm: React.FC = () => {
  const { token } = theme.useToken();
  const [current, setCurrent] = useState(0);
  const [showNextButton, setShowNextButton] = useState(true);

  const next = () => {
    setCurrent(current + 1);
  };

  const prev = () => {
    setCurrent(current - 1);
  };

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
          {/* Второй компонент шага */}
          <Button onClick={() => setShowNextButton(true)}>Change State</Button>
        </>
      ),
    },
    {
      title: 'Согласование командировки',
      content: () => <p>Подождите...</p>,
    },
    {
      title: 'Авансовые средства',
      content: () => (
        <>
          <p>Зачисление авансовых средств</p>
          <ThirdStep />
          <Button onClick={() => setShowNextButton(true)}>Change State</Button>
        </>
      ),
    },
    {
      title: 'Приказ о командировке',
      content: () => (
        <>
          <p>Дождитесь выпуска приказа</p>
          {/* Компонент для этого шага */}
          <ThirdStep />
        </>
      ),
    },
    {
      title: 'Отчётные документы',
      content: () => (
        <>
          <p>Заполните и предоставьте необходимые документы</p>
          {/* Компонент для этого шага */}
          <ThirdStep />
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
    lineHeight: '260px',
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
      <div style={{ marginTop: 24, textAlign: 'right' }}>
        {current > 0 && (
          <Button style={{ margin: '0 8px' }} onClick={() => prev()}>
            Previous
          </Button>
        )}
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
