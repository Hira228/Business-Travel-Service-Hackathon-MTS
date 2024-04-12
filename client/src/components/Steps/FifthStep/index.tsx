import { Button, Card, message } from 'antd';
import jsPDF from 'jspdf';

const FifthStep = () => {
  const createPdf = () => {
    const doc = new jsPDF();
    doc.text('Фамилия Имя Отчество:', 10, 10);
    doc.text('Гражданство:', 10, 20);
    doc.text('Цель командировки:', 10, 30);
    doc.text('Продолжительность командировки:', 10, 40);
    doc.text('Дата начала командировки:', 10, 50);
    doc.text('Дата окончания командировки:', 10, 60);
    doc.text('Место пребывания:', 10, 70);
    doc.text('Срок действия приказа:', 10, 80);
    doc.text('Основание:', 10, 90);

    doc.save('business_trip_order.pdf');
    message.success('PDF документ создан успешно');
  };

  return (
    <Card title="Приказ о командировке">
      <Button onClick={createPdf}>Загрузить приказ</Button>
    </Card>
  );
};

export default FifthStep;
