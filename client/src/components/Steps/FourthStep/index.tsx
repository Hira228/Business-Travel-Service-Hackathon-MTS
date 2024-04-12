import { useState } from 'react';
import { Card, Button, Upload, message } from 'antd';
import { UploadOutlined } from '@ant-design/icons';
import styles from './index.module.css';

const FourthStep = () => {
  const [receivedMoney, setReceivedMoney] = useState('');
  const [spentMoney, setSpentMoney] = useState('');
  const [files, setFiles] = useState([]);

  const handleReceivedMoneyChange = (e) => {
    setReceivedMoney(e.target.value);
  };

  const handleSpentMoneyChange = (e) => {
    setSpentMoney(e.target.value);
  };

  const handleFileChange = (info) => {
    const fileList = [...info.fileList];
    setFiles(fileList);
  };

  const handleSave = () => {
    // Здесь отправляем файлы на сервер
    console.log('Sending files:', files);
    // Сбрасываем состояние после отправки
    setReceivedMoney('');
    setSpentMoney('');
    setFiles([]);
    message.success('Данные успешно сохранены');
  };

  return (
    <div className={styles.container}>
      <Card title="Командировочные расходы" className={styles.card}>
        <Upload
          onChange={handleFileChange}
          multiple={true}
          fileList={files}
          showUploadList={{ showRemoveIcon: true }}>
          <Button icon={<UploadOutlined />}>Загрузить файлы</Button>
        </Upload>
      </Card>
      <Card title="Авансовый отчет" className={styles.card}>
        <div className={styles.inputContainer}>
          <label htmlFor="receivedMoney">Денег получено:</label>
          <input
            id="receivedMoney"
            type="text"
            value={receivedMoney}
            onChange={handleReceivedMoneyChange}
          />
          <span>руб.</span>
        </div>
        <div className={styles.inputContainer}>
          <label htmlFor="spentMoney">Денег израсходовано:</label>
          <input id="spentMoney" type="text" value={spentMoney} onChange={handleSpentMoneyChange} />
          <span>руб.</span>
        </div>
        <Button type="primary" onClick={handleSave}>
          Сохранить
        </Button>
      </Card>
    </div>
  );
};

export default FourthStep;
