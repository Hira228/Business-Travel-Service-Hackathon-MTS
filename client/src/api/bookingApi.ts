import axios from 'axios';
import { APIHOST } from '../utils/consts';


export const fetchCitiesAPI = async () => {
  try {
    const response = await axios.get(`${APIHOST}/booking/get-all-cities-with-hotels`);
    return response.data;
  } catch (error) {
    throw new Error('Failed to fetch cities');
  }
};

export const fetchHotelsAPI = async (city: string) => {
  try {
    const response = await axios.get(`${APIHOST}/booking/get-all-hotels-in-city/${city}`);
    return response.data;
  } catch (error) {
    throw new Error('Failed to fetch hotels');
  }
};

export const createBookingAPI = async (bookingData: any) => {
    try {
      const response = await axios.post(`${APIHOST}/booking/admin/create-booking`, bookingData);
      return response.data;
    } catch (error) {
      throw new Error('Failed to create booking');
    }
  };


export async function getAllCitiesWithHotels(): Promise<string[]> {
    try {
      const response = await fetch(`${APIHOST}/booking/get-all-cities-with-hotels`);
      const data = await response.json();
      return data;
    } catch (error) {
      console.error('Error fetching cities with hotels:', error);
      throw new Error('Failed to fetch cities with hotels');
    }
  }
  

  
  export async function getFlightsInAtoB(cityA: string, cityB: string): Promise<string[]> {
    try {
      const response = await fetch(`${APIHOST}/booking/get-flights-in-a-to-b/${cityA}/${cityB}`);
      const data = await response.json();
      return data;
    } catch (error) {
      console.error('Error fetching flights from city A to B:', error);
      throw new Error('Failed to fetch flights from city A to B');
    }
  }


  export const getAllHotelsInCity = async (city: string) => {
    try {
      const response = await axios.get(`${APIHOST}/booking/get-all-hotels-in-city/${city}`);
      return response.data;
    } catch (error) {
      throw new Error('Failed to fetch hotels in city');
    }
  };


  export const checkBusinessTripApproval = async (): Promise<boolean> => {
    try {
      const response = await axios.get(`${APIHOST}/booking/check-business-trip-approval`);
      return response.data.approved;
    } catch (error) {
      console.error('Error checking business trip approval:', error);
      throw new Error('Failed to check business trip approval');
    }
  };
  