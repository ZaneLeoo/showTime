/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        brand: {
          50:  '#fdf2f4',
          100: '#fce7eb',
          200: '#f9d0d9',
          300: '#f2a4b4',
          400: '#e9788e',
          500: '#c02849',
          600: '#a8324a',
          700: '#8b1a2b',
          800: '#6b1422',
          900: '#4a0e18',
          950: '#3b0810',
        },
        accent: {
          50:  '#fefdf7',
          100: '#fdf9e8',
          200: '#fbf0c4',
          300: '#f7e39e',
          400: '#f0d26e',
          500: '#e5be3d',
          600: '#c9a84c',
          700: '#a88930',
          800: '#8a6f20',
          900: '#6b5518',
          950: '#3d2e0a',
        },
        surface: {
          50:  '#f8fafc',
          100: '#f1f5f9',
          200: '#e2e8f0',
          300: '#cbd5e1',
          700: '#1e293b',
          800: '#1a1f2e',
          850: '#151923',
          900: '#111318',
          950: '#0a0b0f',
        },
        gold: {
          400: '#fbbf24',
          500: '#f59e0b',
          600: '#d97706',
        },
      },
      fontFamily: {
        display: ['"Playfair Display"', 'Georgia', 'serif'],
        body: ['"DM Sans"', '"Noto Sans SC"', 'sans-serif'],
      },
      backgroundImage: {
        'brand-gradient': 'linear-gradient(135deg, #8b1a2b, #c9a84c)',
        'brand-gradient-h': 'linear-gradient(90deg, #8b1a2b, #c9a84c)',
        'gold-gradient': 'linear-gradient(135deg, #c9a84c, #f0d26e)',
        'card-gradient': 'linear-gradient(180deg, rgba(168,50,74,0.08) 0%, rgba(201,168,76,0.04) 100%)',
      },
      boxShadow: {
        'glow': '0 0 40px -10px rgba(168,50,74,0.3)',
        'glow-lg': '0 0 80px -20px rgba(168,50,74,0.4)',
        'glow-gold': '0 0 40px -10px rgba(201,168,76,0.25)',
        'card': '0 1px 3px rgba(0,0,0,0.4), 0 4px 20px rgba(0,0,0,0.3)',
        'card-hover': '0 4px 12px rgba(0,0,0,0.5), 0 8px 40px rgba(168,50,74,0.15)',
      },
      animation: {
        'fade-in': 'fadeIn 0.6s ease-out',
        'slide-up': 'slideUp 0.5s ease-out',
        'slide-down': 'slideDown 0.3s ease-out',
        'shimmer': 'shimmer 2s infinite',
        'pulse-glow': 'pulseGlow 2s ease-in-out infinite',
        'float': 'float 6s ease-in-out infinite',
      },
      keyframes: {
        fadeIn: {
          '0%': { opacity: '0' },
          '100%': { opacity: '1' },
        },
        slideUp: {
          '0%': { opacity: '0', transform: 'translateY(20px)' },
          '100%': { opacity: '1', transform: 'translateY(0)' },
        },
        slideDown: {
          '0%': { opacity: '0', transform: 'translateY(-10px)' },
          '100%': { opacity: '1', transform: 'translateY(0)' },
        },
        shimmer: {
          '0%': { backgroundPosition: '-200% center' },
          '100%': { backgroundPosition: '200% center' },
        },
        pulseGlow: {
          '0%, 100%': { boxShadow: '0 0 20px -5px rgba(168,50,74,0.2)' },
          '50%': { boxShadow: '0 0 40px -5px rgba(168,50,74,0.4)' },
        },
        float: {
          '0%, 100%': { transform: 'translateY(0)' },
          '50%': { transform: 'translateY(-10px)' },
        },
      },
    },
  },
  plugins: [],
}
