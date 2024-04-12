import React, { useState, useEffect } from 'react';
import { Card, Select, Button, message } from 'antd';
import styles from './index.module.css';
import {
  getAllCitiesWithHotels,
  getAllHotelsInCity,
  getFlightsInAtoB,
} from '../../../api/bookingApi';
import { mockCities, mockHotels, mockFlights } from './mockData';
import { createBookingAPI } from '../../../api/bookingApi';

const { Option } = Select;

const SecondStep: React.FC = () => {
  const [cities, setCities] = useState<string[]>([]);
  const [selectedCity, setSelectedCity] = useState<string>('');
  const [selectedHotel, setSelectedHotel] = useState<string>('');
  const [selectedFlight, setSelectedFlight] = useState<string>('');
  const [useMockData, setUseMockData] = useState<boolean>(true);
  const [hotels, setHotels] = useState<string[]>([]);
  const [flights, setFlights] = useState<string[]>([]);
  const [hotelVisible, setHotelVisible] = useState<boolean>(false);
  const [flightVisible, setFlightVisible] = useState<boolean>(false);
  const [buttonVisible, setButtonVisible] = useState<boolean>(false);

  useEffect(() => {
    if (selectedCity) {
      setHotels(mockHotels[selectedCity]);
      setFlights(mockFlights[selectedCity]);
      setHotelVisible(true);
    }
  }, [selectedCity]);

  useEffect(() => {
    if (useMockData) {
      setCities(mockCities);
      setSelectedCity(mockCities[0]);
      setHotels(mockHotels[mockCities[0]]);
      setFlights(mockFlights[mockCities[0]]);
    } else {
      fetchCities();
    }
  }, []);

  useEffect(() => {
    if (selectedCity && selectedHotel && selectedFlight) {
      setButtonVisible(true);
    } else {
      setButtonVisible(false);
    }
  }, [selectedCity, selectedHotel, selectedFlight]);

  const fetchCities = async () => {
    try {
      const response = await getAllCitiesWithHotels();
      setCities(response.data);
    } catch (error) {
      message.error('Failed to fetch cities');
      console.error('Error:', error);
    }
  };

  const fetchHotels = async (city: string) => {
    try {
      const response = await getAllHotelsInCity(city);
      setHotels(response.data);
    } catch (error) {
      message.error('Failed to fetch hotels');
      console.error('Error:', error);
    }
  };

  const fetchFlights = async (city: string) => {
    try {
      const response = await getFlightsInAtoB(city, 'Destination');
      setFlights(response.data);
    } catch (error) {
      message.error('Failed to fetch flights');
      console.error('Error:', error);
    }
  };

  const handleCityChange = async (value: string) => {
    setSelectedCity(value);
    setSelectedHotel('');
    setSelectedFlight('');
    setFlightVisible(false);
    setHotelVisible(true);
    await fetchHotels(value);
  };

  const handleHotelChange = async (value: string) => {
    setSelectedHotel(value);
    setFlightVisible(true);
    await fetchFlights(selectedCity);
  };

  const handleFlightChange = (value: string) => {
    setSelectedFlight(value);
  };

  const handleSave = async () => {
    try {
      const bookingData = {
        city: selectedCity,
        hotel: selectedHotel,
        flight: selectedFlight,
      };
      await createBookingAPI(bookingData);
      message.success('Booking created successfully');
    } catch (error) {
      message.error('Failed to create booking');
      console.error('Error:', error);
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.cardsContainer}>
        <Card title="Выберите город" className={styles.card}>
          <Select
            value={selectedCity}
            placeholder="Выберите город"
            onChange={handleCityChange}
            className={styles.select}>
            {cities.map((city, index) => (
              <Option key={index} value={city}>
                {city}
              </Option>
            ))}
          </Select>
        </Card>
        {hotelVisible && (
          <Card title="Выберите отель" className={styles.card}>
            <Select
              value={selectedHotel}
              placeholder="Выберите отель"
              onChange={handleHotelChange}
              className={styles.select}>
              {hotels.map((hotel, index) => (
                <Option key={index} value={hotel}>
                  {hotel}
                </Option>
              ))}
            </Select>
          </Card>
        )}
        {flightVisible && (
          <Card title="Выберите рейс" className={styles.card}>
            <Select
              value={selectedFlight}
              placeholder="Выберите рейс"
              onChange={handleFlightChange}
              className={styles.select}>
              {flights.map((flight, index) => (
                <Option key={index} value={flight}>
                  {flight}
                </Option>
              ))}
            </Select>
          </Card>
        )}
      </div>
      {buttonVisible && (
        <div className={styles.buttonContainer}>
          <Button type="primary" onClick={handleSave}>
            Сохранить
          </Button>
        </div>
      )}
    </div>
  );
};

export default SecondStep;
