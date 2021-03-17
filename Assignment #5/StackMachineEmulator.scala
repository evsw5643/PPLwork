package edu.colorado.csci3155.project1


sealed trait StackMachineInstruction
case class LoadIns(s: String) extends StackMachineInstruction
case class  StoreIns(s: String) extends StackMachineInstruction
case class PushIns(f: Double) extends StackMachineInstruction
case object AddIns extends StackMachineInstruction
case object SubIns extends StackMachineInstruction
case object MultIns extends StackMachineInstruction
case object DivIns extends StackMachineInstruction
case object ExpIns extends StackMachineInstruction
case object LogIns extends StackMachineInstruction
case object SinIns extends StackMachineInstruction
case object CosIns extends StackMachineInstruction
case object PopIns extends StackMachineInstruction

object StackMachineEmulator {



    /* Function emulateSingleInstruction
        Given a list of doubles to represent a stack
              a map from string to double precision numbers for the environment
        and   a single instruction of type StackMachineInstruction
        Return a tuple that contains the
              modified stack that results when the instruction is executed.
              modified environment that results when the instruction is executed.

        Make sure you handle the error cases: eg., stack size must be appropriate for the instruction
        being executed. Division by zero, log of a non negative number
        Throw an exception or assertion violation when error happens.
     */
    def emulateSingleInstruction(stack: List[Double],
                                 env: Environment.t,
                                 ins: StackMachineInstruction): (List[Double], Environment.t) = {
        ins match {

            case LoadIns(s) => stack match{
                case Nil => throw new IllegalArgumentException()
                case v1 :: tail => tail match{
                    case tail => (tail, Environment.extend(s, v1, env))
                }
            }

            case StoreIns(s) => stack match{
                case _ => {
                    val Dub = Environment.lookup(s, env)
                    (Dub :: stack, env)
                }
            }

            case PushIns(f) => (f :: stack, env)

            case PopIns => stack match {
                case Nil => throw new IllegalArgumentException()
                case _ :: tail => (tail, env)
            }

            case AddIns => stack match {
                case Nil => throw new IllegalArgumentException()
                case v1 :: v2 :: tail => v2 :: tail match {
                    case Nil => throw new IllegalArgumentException()
                    case v2 :: tail => (v1 + v2 :: tail, env)
                }
            }

            case SubIns => stack match {
                case Nil => throw new IllegalArgumentException()
                case v1 :: v2 :: tail => v2 :: tail match {
                    case Nil => throw new IllegalArgumentException()
                    case v2 :: tail => ((v2 - v1) :: tail, env)
                }
            }

            case MultIns => stack match {
                case Nil => throw new IllegalArgumentException()
                case v1 :: v2 :: tail => v2 :: tail match {
                    case Nil => throw new IllegalArgumentException()
                    case v2 :: tail => (v2 * v1 :: tail, env)
                }
            }

            case DivIns => stack match {
                case Nil => throw new IllegalArgumentException()
                case v1 :: v2 :: tail => v2 :: tail match {
                    case Nil => throw new IllegalArgumentException()
                    case v2 :: tail => (v2 / v1 :: tail, env)
                }
            }

            case LogIns => stack match{
                case Nil => throw new IllegalArgumentException()
                case head :: tail => {
                    if(head < 0) throw new IllegalArgumentException()
                    else (scala.math.log(head)::tail, env)
                }
            }

            case ExpIns => stack match{
                case Nil => throw new IllegalArgumentException()
                case head :: tail => (scala.math.exp(head)::tail, env)
            }

            case CosIns => stack match{
                case Nil => throw new IllegalArgumentException()
                case head :: tail => (scala.math.cos(head)::tail, env)
            }

            case SinIns => stack match{
                case Nil => throw new IllegalArgumentException()
                case head :: tail => (scala.math.sin(head)::tail, env)
            }
        }
    }

    /* Function emulateStackMachine
       Execute the list of instructions provided as inputs using the
       emulateSingleInstruction function.
       Use foldLeft over list of instruction rather than a for loop if you can.
       Return value must be a double that is the top of the stack after all instructions
       are executed.
     */
    def emulateStackMachine(instructionList: List[StackMachineInstruction]): Environment.t =
        {
            val initEnv = Environment.empty
            val stack = List.empty[Double]

            val (_,soln) = instructionList.foldLeft((stack, initEnv))((acc: (List[Double], Environment.t), instr: StackMachineInstruction) =>
                emulateSingleInstruction(acc._1, acc._2, instr))
            soln
        }


}