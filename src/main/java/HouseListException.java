public class HouseListException extends Exception {

        private int errCode;

        public HouseListException(int errCode, String message) {
            super(message);
            this.errCode = errCode;
        }

        public int getErrCode() {
            return errCode;
        }

        public void setErrCode(int errCode) {
            this.errCode = errCode;
        }

}
