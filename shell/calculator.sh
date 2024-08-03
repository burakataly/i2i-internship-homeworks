
factorial() {
  if [ $1 -eq 0 ]; then
    echo 1
  else
    echo $(($1 * $(factorial $(($1 - 1)))))
  fi
}

echo "Input calculation operand (+, -, *, /, %, !):"
read operand

if [ "$operand" == "!" ]; then
  echo "Input number:"
  read number1
else
  echo "Input number1:"
  read number1
  echo "Input number2:"
  read number2
fi

if { [ "$operand" == "/" ] || [ "$operand" == "%" ]; } && [ "$number2" == "0" ]; then
   echo "you cannot divide a number to 0 or do a mod operation by 0"
   exit 1
fi

case $operand in
  +)
    result=$(($number1 + $number2))
    ;;
  -)
    result=$(($number1 - $number2))
    ;;
  \*)
    result=$(($number1 * $number2))
    ;;
  /)
    result=$(($number1 / $number2))
    ;;
  %)
    result=$(($number1 % $number2))
    ;;
  !)
    result=$(factorial $number1)
    ;;
  *)
  echo "Invalid operand."
    exit 1
    ;;
esac

echo "Result: $result"
