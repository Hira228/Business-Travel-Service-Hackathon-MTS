import React, { useState, useEffect } from 'react';
import { Table, Button, Space, Card } from 'antd';
import { Link } from 'react-router-dom';
import { fetchBusinessTripsForApproval } from '../../api/businessTripApi';
import styles from './index.module.css';
const generateBusinessTrips = () => {
  const businessTrips = [];

  for (let i = 0; i < 20; i++) {
    const id = `${i + 1}`;
    const performerName = `Исполнитель ${i + 1}`;
    const leaderName = `Руководитель ${i + 1}`;
    const startDate = new Date(Date.now() + i * 24 * 60 * 60 * 1000).toLocaleDateString();
    const endDate = new Date(Date.now() + (i + 5) * 24 * 60 * 60 * 1000).toLocaleDateString();
    const documents = [
      {
        name: 'Договор',
        link: `https://example.com/contract_${i + 1}.pdf`,
      },
      {
        name: 'Служебная записка',
        link: `https://example.com/memo_${i + 1}.pdf`,
      },
    ];

    businessTrips.push({
      id,
      performerName,
      leaderName,
      startDate,
      endDate,
      documents,
    });
  }

  return businessTrips;
};

const LeaderDashboard = () => {
  const [businessTrips, setBusinessTrips] = useState([]);

  useEffect(() => {
    fetchBusinessTrips();
    setBusinessTrips(generateBusinessTrips());
  }, []);

  const fetchBusinessTrips = async () => {
    try {
      const trips = await fetchBusinessTripsForApproval();
      setBusinessTrips(trips);
    } catch (error) {
      console.error('Failed to fetch business trips:', error);
    }
  };

  const columns = [
    {
      title: 'ID командировки',
      dataIndex: 'id',
      key: 'id',
    },
    {
      title: 'ФИО исполнителя',
      dataIndex: 'performerName',
      key: 'performerName',
    },
    {
      title: 'ФИО руководителя',
      dataIndex: 'leaderName',
      key: 'leaderName',
    },
    {
      title: 'Дата начала',
      dataIndex: 'startDate',
      key: 'startDate',
    },
    {
      title: 'Дата окончания',
      dataIndex: 'endDate',
      key: 'endDate',
    },
    {
      title: 'Документы',
      dataIndex: 'documents',
      key: 'documents',
      render: (documents) => (
        <Space size="middle">
          {documents.map((doc, index) => (
            <a key={index} href={doc.link} target="_blank" rel="noopener noreferrer">
              {doc.name}
            </a>
          ))}
        </Space>
      ),
    },
  ];

  return (
    <div className={styles.container}>
      <Card title="Дашборд для утверждения командировок">
        <Table dataSource={businessTrips} columns={columns} pagination={false} />
      </Card>
      <Card title="Создание новой командировки" className={styles.creation}>
        <Link to="/journey">
          <Button type="primary">Создать новую командировку</Button>
        </Link>
      </Card>
    </div>
  );
};

export default LeaderDashboard;
