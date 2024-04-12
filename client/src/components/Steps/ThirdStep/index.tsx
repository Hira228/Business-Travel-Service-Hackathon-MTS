import React, { useState, useEffect } from 'react';
import { Card, Spin, message } from 'antd';
import { useNavigate } from 'react-router-dom';
import { checkBusinessTripApproval } from '../../../api/bookingApi';

const fakeApproval = () => {
  return Math.random() < 0.5;
};

const ThirdStep: React.FC = () => {
  const [loading, setLoading] = useState<boolean>(true);
  const [approved, setApproved] = useState<boolean | null>(null);
  const navigation = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        try {
          const isApproved = await checkBusinessTripApproval();
          setApproved(isApproved);
          setLoading(false);
          if (!isApproved) {
            // Если командировка не одобрена, перенаправляем на первый шаг
            message.warning('Ваша командировка не одобрена. Перенаправляем на первый шаг.');
            setTimeout(() => {
              navigation('/first-step');
            }, 3000); // Таймаут для перенаправления
          } else {
            message.success('Ваша командировка одобрена! Фанфары!');
          }
        } catch (error) {
          console.error('Error:', error);
          message.error('Ошибка при проверке одобрения командировки');
          setLoading(false);
        }
      } catch (error) {
        console.error('Error:', error);
      }
    };
    setTimeout(() => {
      setApproved(fakeApproval());
      setLoading(false);
    }, 2000);

    fetchData();
  }, []);

  return (
    <Card title="Подождите" style={{ width: '100%', textAlign: 'center' }}>
      <Spin spinning={loading} />
      {approved !== null && (
        <p>{approved ? 'Ваша командировка одобрена! 🎉' : 'Ваша командировка не одобрена. 😔'}</p>
      )}
    </Card>
  );
};

export default ThirdStep;
