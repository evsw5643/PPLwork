package edu.colorado.csci3155.project1


object StackMachineCompiler {



    /* Function compileToStackMachineCode
        Given expression e as input, return a corresponding list of stack machine instructions.
        The type of stackmachine instructions are in the file StackMachineEmulator.scala in this same directory
        The type of Expr is in the file Expr.scala in this directory.
     */
    def compileToStackMachineCode(e: Expr): List[StackMachineInstruction] = {
        e match {
            case Const(f) => List(PushIns(f))

            case Ident(id) => List(StoreIns(id))

            case Plus(e1, e2) => {
                val L1 = compileToStackMachineCode(e1)
                val L2 = compileToStackMachineCode(e2)
                L1 ++ L2 ++ List(AddIns)
            }

            case Minus(e1, e2) => {
                val L1 = compileToStackMachineCode(e1)
                val L2 = compileToStackMachineCode(e2)
                L1 ++ L2 ++ List(SubIns)
            }

            case Mult(e1, e2) => {
                val L1 = compileToStackMachineCode(e1)
                val L2 = compileToStackMachineCode(e2)
                L1 ++ L2 ++ List(MultIns)
            }

            case Div(e1, e2) => {
                val L1 = compileToStackMachineCode(e1)
                val L2 = compileToStackMachineCode(e2)
                L1 ++ L2 ++ List(DivIns)
            }

            case Exp(e1) => {
                val L1 = compileToStackMachineCode(e1)
                L1 ++ List(ExpIns)
            }

            case Log(e) => {
                val L1 = compileToStackMachineCode(e)
                L1 ++ List(LogIns)
            }

            case Sine(e) => {
                val L1 = compileToStackMachineCode(e)
                L1 ++ List(SinIns)
            }

            case Cosine(e) => {
                val L1 = compileToStackMachineCode(e)
                L1 ++ List(CosIns)
            }

            case Let(ident, e1, e2) => {
                val L1 = compileToStackMachineCode(e1)
                val L2 = compileToStackMachineCode(e2)
                L1 ++ List(LoadIns(s"$ident")) ++ L2
            }
        }
    }
}
