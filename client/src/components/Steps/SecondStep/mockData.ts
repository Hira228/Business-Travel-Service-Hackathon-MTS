export const mockHotels: Record<string, string[]> = {
  'New York': ['Hilton New York', 'Marriott Marquis', 'The Plaza Hotel'],
  London: ['The Ritz London', 'InterContinental London', 'The Savoy'],
  Paris: ['Hotel de Crillon', 'Four Seasons Hotel George V', 'Le Meurice'],
  Tokyo: ['The Tokyo Station Hotel', 'The Peninsula Tokyo', 'Park Hyatt Tokyo'],
  Sydney: ['Shangri-La Hotel Sydney', 'InterContinental Sydney', 'The Langham Sydney'],
};

export const mockFlights: Record<string, string[]> = {
  'New York': ['Flight NY101', 'Flight NY202', 'Flight NY303'],
  London: ['Flight LD404', 'Flight LD505', 'Flight LD606'],
  Paris: ['Flight PR707', 'Flight PR808', 'Flight PR909'],
  Tokyo: ['Flight TK1010', 'Flight TK1111', 'Flight TK1212'],
  Sydney: ['Flight SY1313', 'Flight SY1414', 'Flight SY1515'],
};

  export const mockCities = Object.keys(mockHotels); 
  