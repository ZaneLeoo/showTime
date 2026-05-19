/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        primary: {
          50:  '#fff5f5',
          100: '#ffe3e3',
          200: '#ffc9c9',
          300: '#ffa8a8',
          400: '#ff8787',
          500: '#FF6B6B',  // 珊瑚橙主色
          600: '#fa5252',
          700: '#e03131',
          800: '#c92a2a',
          900: '#b02525',
        },
        mint: {
          50:  '#f0fdfa',
          100: '#ccfbef',
          200: '#99f6e0',
          300: '#5fe9ce',
          400: '#4ECDC4',  // 薄荷绿辅助
          500: '#3dbdb5',
          600: '#2ea39b',
          700: '#1e7e78',
          800: '#16615c',
          900: '#0f4e4a',
        },
        surface: {
          light: '#F7F9FC',  // 极浅灰蓝背景
          card:  '#FFFFFF',
          dark:  '#2D3436',   // 深灰黑文字
          muted: '#636E72',   // 次要灰色
          line:  '#E8ECF0',   // 边框
        },
      },
      fontFamily: {
        sans: ['"Noto Sans SC"', '"Inter"', 'system-ui', 'sans-serif'],
      },
      borderRadius: {
        pill: '30px',
        card: '16px',
      },
      boxShadow: {
        card: '0 2px 16px rgba(0, 0, 0, 0.06)',
        'card-hover': '0 8px 32px rgba(0, 0, 0, 0.10)',
        nav: '0 1px 0 rgba(0, 0, 0, 0.05)',
      },
    },
  },
  plugins: [],
}
