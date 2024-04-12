import { APIHOST } from "../utils/consts";



const sendPassportData = async (passportNumber: string, firstName: string, lastName: string, dateOfBirth: string, token: string) => {
  try {
    const response = await fetch(`${APIHOST}/passport/create-passport-data`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      },
      body: JSON.stringify({
        passportNumber,
        firstName,
        lastName,
        dateOfBirth
      })
    });
    if (!response.ok) {
      throw new Error('Failed to send passport data');
    }
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error sending passport data:', error);
    throw error;
  }
};

const getPassportData = async (passportNumber: string, token: string) => {
  try {
    const response = await fetch(`${APIHOST}/passport/get-passport-data?passportNumber=${passportNumber}`, {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });
    if (!response.ok) {
      throw new Error('Failed to get passport data');
    }
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error getting passport data:', error);
    throw error;
  }
};

export { sendPassportData, getPassportData };
