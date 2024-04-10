import { useEffect } from 'react';

function NotFound() {
  useEffect(() => {
    window.location.href = 'https://www.youtube.com/watch?v=dQw4w9WgXcQ&autoplay=1';
  });
  return <div></div>;
}

export default NotFound;
