const Welcome = () => import('~/pages/welcome').then(m => m.default || m)
const Login = () => import('~/pages/auth/login').then(m => m.default || m)
const Register = () => import('~/pages/auth/register').then(m => m.default || m)
const PasswordEmail = () => import('~/pages/auth/password/email').then(m => m.default || m)
const PasswordReset = () => import('~/pages/auth/password/reset').then(m => m.default || m)
const NotFound = () => import('~/pages/errors/404').then(m => m.default || m)

const Dashboard = () => import('~/pages/dashboard').then(m => m.default || m)
const ReservationsTable = () => import('~/pages/reservations_table').then(m => m.default || m)
const ReservationsPage = () => import('~/pages/reservations_page').then(m => m.default || m)
const AddReservation = () => import('~/pages/add_reservation').then(m => m.default || m)

export default [
  { path: '/', name: 'dashboard', component: Dashboard },
  { path: '/reservations_table', name: 'reservations_table', component: ReservationsTable },
  { path: '/new_reservation', name: 'new_reservation', component: ReservationsPage },
  { path: '/add_reservation', name: 'add_reservation', component: AddReservation },
  // { path: '/new_entry', name: 'new_entry', component: NewEntry },
  // { path: '/leaderboard', name: 'leaderboard', component: Leaderboard },

  { path: '/login', name: 'login', component: Login },
  // { path: '/register', name: 'register', component: Register },


  { path: '/password/reset', name: 'password.request', component: PasswordEmail },
  { path: '/password/reset/:token', name: 'password.reset', component: PasswordReset },


  { path: '*', component: NotFound }
]
